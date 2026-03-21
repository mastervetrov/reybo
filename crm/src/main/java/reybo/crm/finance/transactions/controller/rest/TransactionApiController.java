package reybo.crm.finance.transactions.controller.rest;

import reybo.crm.common.api.service.BaseCrudService;
import reybo.crm.common.rest.controller.BaseRestController;
import reybo.crm.finance.transactions.dto.TransactionDto;
import reybo.crm.finance.transactions.model.Transaction;
import reybo.crm.finance.transactions.request.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crm/transaction")
@Slf4j
public class TransactionApiController extends BaseRestController<TransactionRequest, Transaction, TransactionDto> {

    public TransactionApiController(BaseCrudService<TransactionRequest, Transaction, TransactionDto, ?, ?> service) {
        super(service);
    }

}
