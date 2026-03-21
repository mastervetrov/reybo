package reybo.crm.settings.company.dto;

import reybo.crm.customer.model.CompanyContractor;
import reybo.crm.settings.company.model.Company;
import lombok.Data;

import java.util.UUID;

/**
 * Response DTO, contains parameters table "company".
 * Used for company settings return.
 *
 * @see Company
 * @see CompanyContractor
 * @see CompanyService
 */
@Data
public class CompanyDto {

  private UUID id;

  private String name;

  private String inn;

  private String kpp;

  private String ogrn;

  private String address;

  private String email;

  private String phone;

  private CurrencyDto currency;

  private boolean supportAccess;

  private String comment;

}
