package reybo.crm.common.api.mapper;

import java.util.List;

import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Base mapper.
 * Mapping Dto, Entity and Request.
 *
 * @param <REQUEST> request.
 * @param <ENTITY> entity.
 * @param <DTO> dto.
 */
public interface BaseMapper<REQUEST, ENTITY, DTO> {

  /**
   * Mapping user request {@link REQUEST} to database entity {@link ENTITY}.
   *
   * @param request users request {@link REQUEST}.
   * @return database entity {@link ENTITY}.
   */
  ENTITY requestToEntity(REQUEST request);

  /**
   * Mapping database entity {@link ENTITY} to dto response {@link DTO}.
   *
   * @param entity database entity {@link ENTITY}
   * @return dto response {@link DTO}
   */
  DTO entityToDto(ENTITY entity);


  /**
   * Mapping database entity list {@link ENTITY} to dto response list {@link DTO}.
   *
   * @param entityList database entity list {@link ENTITY}
   * @return dto response list {@link ENTITY}
   */
  List<DTO> entityListToDtoList(List<ENTITY> entityList);

  /**
   * Mapping database entity page {@link ENTITY} to dto response page {@link DTO}.
   *
   * @param entityPage database entity page {@link ENTITY}.
   * @return dto response page {@link DTO}.
   */
  default Page<DTO> entityPageToDtoPage(Page<ENTITY> entityPage) {
      List<DTO> dtoList = entityListToDtoList(entityPage.getContent());
      return new PageImpl<>(dtoList, entityPage.getPageable(), entityPage.getTotalElements());
  }

  ENTITY updateEntityFromRequest(@MappingTarget ENTITY existEntity, REQUEST request);
}
