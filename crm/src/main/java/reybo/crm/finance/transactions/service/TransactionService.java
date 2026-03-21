package reybo.crm.finance.transactions.service;

import reybo.crm.common.api.service.BaseCrudService;
import reybo.crm.finance.transactions.dto.TransactionDto;
import reybo.crm.finance.transactions.mapper.TransactionMapper;
import reybo.crm.finance.transactions.model.Transaction;
import reybo.crm.finance.transactions.repository.TransactionRepository;
import reybo.crm.finance.transactions.request.TransactionRequest;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends BaseCrudService<TransactionRequest, Transaction, TransactionDto, TransactionMapper, TransactionRepository> {
    public TransactionService(TransactionMapper mapper, TransactionRepository repository, Validator validator) {
        super(mapper, repository, validator);
    }
}
