package reybo.crm.store.dto;

import lombok.Data;

import java.util.UUID;

/**
 * Response DTO - contains data store.
 * Used for return in controller.
 *
 */
@Data
public class StoreDto {

  private UUID id;

  private String name;

}
