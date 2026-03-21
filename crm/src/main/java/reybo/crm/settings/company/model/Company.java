package reybo.crm.settings.company.model;

import reybo.crm.settings.company.dto.CompanyDto;
import reybo.crm.settings.company.mapper.CompanyMapper;
import reybo.crm.settings.company.repository.CompanyRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Entity company.
 * Contains company settings.
 *
 * @see CompanyRepository
 * @see CompanyService
 * @see CompanyMapper
 * @see CompanyDto
 */
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  private String inn;

  private String kpp;

  private String ogrn;

  private String address;

  private String email;

  private String phone;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "currency_id")
  private Currency currency;

  private boolean supportAccess;

  private String comment;

}
