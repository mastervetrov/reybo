package reybo.crm.store.mapper;

import reybo.crm.common.api.mapper.BaseMapper;
import reybo.crm.store.dto.ReceiptDto;
import reybo.crm.store.model.Receipt;
import reybo.crm.store.request.ReceiptRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for entity {@link Receipt}.
 *
 * @see Receipt
 * @see ReceiptDto
 * @see ReceiptRequest
 * @see ReceiptService
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReceiptMapper extends BaseMapper<ReceiptRequest, Receipt, ReceiptDto> {}
