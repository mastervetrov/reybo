package reybo.crm.product.repository;

import reybo.crm.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for Product entity.
 *
 * @see Product
 * @see ProductService
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
