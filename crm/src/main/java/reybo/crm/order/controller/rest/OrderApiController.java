package reybo.crm.order.controller.rest;

import reybo.crm.common.rest.controller.BaseRestController;
import reybo.crm.common.api.service.BaseCrudService;
import reybo.crm.order.dto.ServiceOrderDto;
import reybo.crm.order.model.ServiceOrder;
import reybo.crm.order.request.ServiceOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crm/orders")
@Slf4j
public class OrderApiController extends BaseRestController<ServiceOrderRequest, ServiceOrder, ServiceOrderDto> {

    public OrderApiController(BaseCrudService<ServiceOrderRequest, ServiceOrder, ServiceOrderDto, ?, ?> service) {
        super(service);
    }

}
