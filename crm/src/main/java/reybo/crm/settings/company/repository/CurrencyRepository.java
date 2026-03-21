package reybo.crm.settings.company.repository;

import reybo.crm.settings.company.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository Currency entity.
 *
 * @see CurrencyService
 * @see Currency
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, UUID> {
}
