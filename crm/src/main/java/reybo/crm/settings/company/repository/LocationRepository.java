package reybo.crm.settings.company.repository;

import reybo.crm.settings.company.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository Location entity.
 *
 * @see LocationService
 * @see Location
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
}
