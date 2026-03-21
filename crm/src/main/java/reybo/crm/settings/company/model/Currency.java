package reybo.crm.settings.company.model;

import reybo.crm.settings.company.dto.CurrencyDto;
import reybo.crm.settings.company.mapper.CurrencyMapper;
import reybo.crm.settings.company.repository.CurrencyRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Entity currency.
 * Contains currency for {@link Company}
 *
 * @see CurrencyRepository
 * @see CurrencyService
 * @see CurrencyMapper
 * @see CurrencyDto
 */
@Getter
@Setter
@Entity
@Table(name = "currency")
public class Currency {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  private String value;

}
