package reybo.crm.store.dto;

import reybo.crm.settings.company.dto.LocationDto;
import reybo.crm.store.model.Receipt;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Response Dto - contains receipt data.
 * Used for return data in controller.
 *
 * @see ReceiptService
 * @see Receipt
 */
@Data
public class ReceiptDto {
  private UUID id;
  private String name;
  private LocalDateTime dateTime;
  private LocationDto locationDto;
  private StoreDto storeDto;
  private BigDecimal sum;
  private BigDecimal paid;
  private String comment;
  private List<ProductReceiptDto> products;
}
