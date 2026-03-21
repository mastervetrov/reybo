package reybo.crm.settings.company.mapper;

import reybo.crm.common.api.mapper.BaseMapper;
import reybo.crm.settings.company.dto.LocationDto;
import reybo.crm.settings.company.model.Location;
import reybo.crm.settings.company.request.LocationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for entity {@link Location}.
 *
 * @see Location
 * @see LocationDto
 * @see LocationRequest
 * @see LocationService
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationMapper extends BaseMapper<LocationRequest, Location, LocationDto> {
}
