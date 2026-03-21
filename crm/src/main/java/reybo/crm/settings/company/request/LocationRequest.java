package reybo.crm.settings.company.request;

import lombok.Data;

/**
 * Request - contains location fields.
 * Used for update and create location.
 * todo supplement to the javadoc controller
 *
 * @see LocationService
 */
@Data
public class LocationRequest {
  private String name;
  private String color;
  private String address;
  private String phoneNumber;

}
