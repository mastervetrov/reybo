package reybo.crm.settings.company.dto;

import lombok.Data;

import java.util.UUID;

/**
 * Response Dto - contains location data.
 * Used for return to the controller
 *
 */
@Data
public class LocationDto {

  private UUID id;
  private String name;
  private String color;
  private String address;
  private String phoneNumber;

}
