package reybo.crm.common.api.service;

import reybo.crm.common.api.mapper.BaseMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Абстрактный CRUD сервис для переиспользования.
 * Реализует базовые операции создания, чтения, обновления и удаления.
 *
 * @param <REQUEST> тип запроса (DTO для создания/обновления)
 * @param <ENTITY> тип сущности JPA
 * @param <DTO> тип ответа (DTO для чтения)
 * @param <MAPPER> маппер между REQUEST/ENTITY/DTO
 * @param <REPOSITORY> JPA репозиторий с UUID
 */
@RequiredArgsConstructor
@Slf4j
public abstract class BaseCrudService<
        REQUEST,
        ENTITY,
        DTO,
        MAPPER extends BaseMapper<REQUEST, ENTITY, DTO>,
        REPOSITORY extends JpaRepository<ENTITY, UUID>  // 👈 UUID жестко!
        > implements ReadService<DTO>, WriteService<REQUEST, DTO> {

    protected final MAPPER mapper;
    protected final REPOSITORY repository;
    protected final Validator validator;

    // === РЕАЛИЗАЦИЯ ReadService ===

    @Override
    public DTO findById(UUID id) {  // 👈 UUID вместо ID
        Assert.notNull(id, "UUID must not be null");

        log.debug("Finding entity by id: {}", id);
        ENTITY entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Entity with UUID %s not found", id)));

        DTO dto = mapper.entityToDto(entity);
        log.debug("Found entity: {}", dto);

        return dto;
    }

    @Override
    public List<DTO> findAll() {
        log.debug("Finding all entities");
        List<ENTITY> entities = repository.findAll();
        List<DTO> dtos = mapper.entityListToDtoList(entities);
        log.debug("Found {} entities", dtos.size());

        return dtos;
    }

    @Override
    public Page<DTO> findAll(Pageable pageable) {
        Assert.notNull(pageable, "Pageable must not be null");

        log.debug("Finding all entities with pageable: {}", pageable);
        Page<ENTITY> entityPage = repository.findAll(pageable);
        Page<DTO> dtoPage = entityPage.map(mapper::entityToDto);
        log.debug("Found {} entities on page {}",
                dtoPage.getNumberOfElements(), dtoPage.getNumber());

        return dtoPage;
    }

    @Override
    public List<DTO> findAll(Sort sort) {
        Assert.notNull(sort, "Sort must not be null");

        log.debug("Finding all entities with sort: {}", sort);
        List<ENTITY> entities = repository.findAll(sort);
        List<DTO> dtos = mapper.entityListToDtoList(entities);

        return dtos;
    }

    // === РЕАЛИЗАЦИЯ WriteService ===

    @Override
    @Transactional
    public DTO create(REQUEST request) {
        Assert.notNull(request, "Request must not be null");

        log.debug("Creating entity from request: {}", request);

        validateRequest(request);

        ENTITY entity = mapper.requestToEntity(request);

        beforeCreate(entity, request);
        prepareForCreate(entity);

        ENTITY savedEntity = repository.save(entity);

        afterCreate(savedEntity, request);

        DTO dto = mapper.entityToDto(savedEntity);

        log.info("Created entity {} with id: {}",
                savedEntity.getClass().getSimpleName(),
                getEntityId(savedEntity));

        return dto;
    }

    @Override
    @Transactional
    public DTO update(UUID id, REQUEST request) {  // 👈 UUID вместо ID
        Assert.notNull(id, "UUID must not be null");
        Assert.notNull(request, "Request must not be null");

        log.debug("Updating entity with UUID {} from request: {}", id, request);

        validateRequest(request);

        ENTITY existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Entity with UUID %s not found", id)));

        beforeUpdate(existingEntity, request);

        mapper.updateEntityFromRequest(existingEntity, request);
        prepareForUpdate(existingEntity);

        ENTITY updatedEntity = repository.save(existingEntity);

        afterUpdate(updatedEntity, request);

        DTO dto = mapper.entityToDto(updatedEntity);

        log.info("Updated entity {} with id: {}",
                updatedEntity.getClass().getSimpleName(), id);

        return dto;
    }

    @Override
    @Transactional
    public void delete(UUID id) {  // 👈 UUID вместо ID
        Assert.notNull(id, "UUID must not be null");

        log.debug("Deleting entity with id: {}", id);

        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(
                    String.format("Entity with UUID %s not found", id));
        }

        Optional<ENTITY> entityOptional = repository.findById(id);
        entityOptional.ifPresent(entity -> beforeDelete(entity));

        repository.deleteById(id);

        afterDelete(id);

        log.info("Deleted entity with id: {}", id);
    }

    @Transactional
    public void deleteAllById(Iterable<UUID> ids) {  // 👈 Iterable<UUID>
        Assert.notNull(ids, "IDs must not be null");

        log.debug("Deleting entities with ids: {}", ids);

        List<ENTITY> entities = repository.findAllById(ids);

        beforeDeleteAll(entities);

        repository.deleteAllById(ids);

        afterDeleteAll(ids);

        log.info("Deleted {} entities", entities.size());
    }

    // === ДОПОЛНИТЕЛЬНЫЕ МЕТОДЫ ===

    @Transactional
    public List<DTO> createAll(List<REQUEST> requests) {
        Assert.notNull(requests, "Requests must not be null");

        log.debug("Creating {} entities", requests.size());

        requests.forEach(this::validateRequest);

        List<ENTITY> entities = requests.stream()
                .map(mapper::requestToEntity)
                .collect(Collectors.toList());

        entities.forEach(this::prepareForCreate);
        beforeCreateAll(entities, requests);

        List<ENTITY> savedEntities = repository.saveAll(entities);

        afterCreateAll(savedEntities, requests);

        List<DTO> dtos = mapper.entityListToDtoList(savedEntities);

        log.info("Created {} entities", dtos.size());

        return dtos;
    }

    // === ЗАЩИЩЕННЫЕ МЕТОДЫ ДЛЯ ПЕРЕОПРЕДЕЛЕНИЯ ===

    protected void validateRequest(REQUEST request) {
        if (validator != null) {
            Set<ConstraintViolation<REQUEST>> violations = validator.validate(request);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        }
    }

    /**
     * Получить UUID из сущности (для логирования)
     */
    protected UUID getEntityId(ENTITY entity) {
        throw new UnsupportedOperationException(
                "getEntityId() must be implemented in concrete service");
    }

    protected void prepareForCreate(ENTITY entity) {
        // Базовая реализация для Auditable сущностей
    }

    protected void prepareForUpdate(ENTITY entity) {
    }

    protected void prepareForPatch(ENTITY entity) {
    }

    // === ХУКИ ДЛЯ ПЕРЕОПРЕДЕЛЕНИЯ ===

    protected void beforeCreate(ENTITY entity, REQUEST request) { }
    protected void afterCreate(ENTITY entity, REQUEST request) { }
    protected void beforeUpdate(ENTITY entity, REQUEST request) { }
    protected void afterUpdate(ENTITY entity, REQUEST request) { }
    protected void beforePatch(ENTITY entity, REQUEST request) { }
    protected void afterPatch(ENTITY entity, REQUEST request) { }
    protected void beforeDelete(ENTITY entity) { }
    protected void afterDelete(UUID id) { }
    protected void beforeCreateAll(List<ENTITY> entities, List<REQUEST> requests) { }
    protected void afterCreateAll(List<ENTITY> entities, List<REQUEST> requests) { }
    protected void beforeDeleteAll(List<ENTITY> entities) { }
    protected void afterDeleteAll(Iterable<UUID> ids) { }
}