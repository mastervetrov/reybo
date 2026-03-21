package reybo.crm.settings.company.request;

import lombok.Data;

/**
 * Request - contains currency fields.
 * Used for update and create currency
 * todo supplement to the javadoc controller
 *
 * @see CurrencyService
 */
@Data
public class CurrencyRequest {

  private String name;

  private String value;
}
