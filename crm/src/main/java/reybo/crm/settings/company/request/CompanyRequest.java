package reybo.crm.settings.company.request;

import reybo.crm.settings.company.model.Currency;
import lombok.Data;

/**
 * Request - contains company fields.
 * Used for update and create company.
 * todo supplement to the javadoc controller
 *
 * @see LocationService
 */
@Data
public class CompanyRequest {

  private String name;

  private String inn;

  private String kpp;

  private String ogrn;

  private String address;

  private String email;

  private String phone;

  private Currency currency;

  private boolean supportAccess;

  private String comment;
}
