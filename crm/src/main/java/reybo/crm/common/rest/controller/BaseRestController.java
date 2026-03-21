package reybo.crm.common.rest.controller;

import reybo.crm.common.api.service.BaseCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class BaseRestController<REQUEST, ENTITY, DTO> implements BaseOperations<DTO, REQUEST> {

    // ? ? репозиторий и маппер, которые по умолчанию используются только в сервисном слое
    private final BaseCrudService<REQUEST, ENTITY, DTO, ?, ?> service;

    @Override
    @GetMapping
    public Page<DTO> getAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @Override
    @GetMapping("/{id}")
    public DTO getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @Override
    @PostMapping
    public DTO create(@RequestBody REQUEST request) {
        return service.create(request);
    }

    @Override
    @PutMapping("/{id}")
    public DTO  update(@PathVariable UUID id, @RequestBody REQUEST request) {
        return service.update(id, request);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}