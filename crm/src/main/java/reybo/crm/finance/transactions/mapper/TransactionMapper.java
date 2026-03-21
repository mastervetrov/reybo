package reybo.crm.finance.transactions.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import reybo.crm.common.api.mapper.BaseMapper;
import reybo.crm.finance.transactions.dto.TransactionDto;
import reybo.crm.finance.transactions.model.Transaction;
import reybo.crm.finance.transactions.request.TransactionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper extends BaseMapper<TransactionRequest, Transaction, TransactionDto> {

    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()); //JavaTimeModule required for mapping LocalDataTime

}
