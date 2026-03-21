package reybo.crm.store.repository;

import reybo.crm.store.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for ReceiptEntity.
 *
 * @see Receipt
 */
@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {
}
