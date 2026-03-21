package reybo.crm.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import reybo.crm.product.model.Product;
import lombok.Data;

/**
 * Response Dto - contains product data.
 * Used for return to the controllers.
 *
 * @see Product
 * @see ProductService
 * @see RemainderPageController
 * @see RemainderFragmentController
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductDto {
  private UUID id;
  private String name;
  private String description;
  private BigDecimal retailPrice;
  private BigDecimal dealerPrice;
  private BigDecimal partnerPrice;
  private Integer count;
  private Boolean isNew;
  private Boolean isHit;
  private String picture;
  private String category;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


}
