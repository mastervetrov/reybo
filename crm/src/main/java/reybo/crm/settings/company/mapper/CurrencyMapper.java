package reybo.crm.settings.company.mapper;

import reybo.crm.settings.company.request.CurrencyRequest;
import reybo.crm.settings.company.dto.CurrencyDto;
import reybo.crm.common.api.mapper.BaseMapper;
import reybo.crm.settings.company.model.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for entity {@link Currency}.
 *
 * @see Currency
 * @see CurrencyDto
 * @see CurrencyRequest
 * @see CurrencyService
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurrencyMapper extends BaseMapper<CurrencyRequest, Currency, CurrencyDto> {}
