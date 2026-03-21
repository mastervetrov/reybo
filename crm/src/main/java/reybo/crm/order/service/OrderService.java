package reybo.crm.order.service;

import reybo.crm.common.api.service.BaseCrudService;
import reybo.crm.order.dto.ServiceOrderDto;
import reybo.crm.order.mapper.ServiceOrderMapper;
import reybo.crm.order.model.ServiceOrder;
import reybo.crm.order.repository.ServiceOrderRepository;
import reybo.crm.order.request.ServiceOrderRequest;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends BaseCrudService<ServiceOrderRequest, ServiceOrder, ServiceOrderDto, ServiceOrderMapper, ServiceOrderRepository> {
    public OrderService(ServiceOrderMapper mapper, ServiceOrderRepository repository, Validator validator) {
        super(mapper, repository, validator);
    }
}
