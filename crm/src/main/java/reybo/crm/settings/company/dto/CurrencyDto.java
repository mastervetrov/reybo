package reybo.crm.settings.company.dto;

import reybo.crm.settings.company.model.Currency;
import reybo.crm.settings.company.mapper.CurrencyMapper;
import lombok.Data;

import java.util.UUID;

/**
 * Response DTO, contains parameters table "currency".
 * Used for company settings return.
 *
 * @see Currency
 * @see CurrencyMapper
 * @see CurrencyService
 */
@Data
public class CurrencyDto {

  private UUID id;

  private String name;

  private String value;

}
