package reybo.crm.store.request;

import reybo.crm.settings.company.dto.LocationDto;
import reybo.crm.store.dto.StoreDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import reybo.crm.store.model.Receipt;
import lombok.Data;

/**
 * Request - contains receipts data.
 * Used for save data in service.
 *
 * @see Receipt
 */
@Data
public class ReceiptRequest {

  private UUID id;

  private String name;

  private LocalDateTime dateTime;

  private LocationDto locationDto;

  private StoreDto storeDto;

  private BigDecimal sum;

  private BigDecimal paid;

  private String comment;

}
