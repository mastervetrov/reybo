package reybo.crm.order.repository;

import reybo.crm.order.model.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, UUID> {
}
