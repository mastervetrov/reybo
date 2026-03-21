package reybo.crm.settings.company.repository;

import reybo.crm.settings.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository Company entity.
 *
 * @see CompanyService
 * @see Company
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
