package reybo.crm.order.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import reybo.crm.common.api.mapper.BaseMapper;
import reybo.crm.order.dto.ServiceOrderDto;
import reybo.crm.order.model.ServiceOrder;

import reybo.crm.order.request.ServiceOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceOrderMapper extends BaseMapper<ServiceOrderRequest, ServiceOrder, ServiceOrderDto> {

    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()); //JavaTimeModule required for mapping LocalDataTime

}
