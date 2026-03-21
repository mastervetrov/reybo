package reybo.crm.product.exception;

import reybo.crm.product.model.Product;
import jakarta.persistence.EntityNotFoundException;

/**
* Exception occurs if {@link Product} not found.
*/
public class ProductEntityNotFoundException extends EntityNotFoundException {
  /**
  * Error message.
  *
  * @param message text error message.
  */
  public ProductEntityNotFoundException(String message) {
    super(message);
  }

}
