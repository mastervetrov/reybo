package reybo.crm.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;

/**
 * Entity contractor.
 * Contains contractor data.
 * Child tables: {@link CompanyContractor} and {@link PrivateContractor}
 */
@Entity
@Table(name = "contractor")
@Inheritance(strategy = InheritanceType.JOINED)
public class Contractor {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String name;
  private List<String> phoneNumberList;
  private String email;
  private String address;
  private String note;
  private boolean isProvider;
  private boolean isPayer;
  //  private AdvertisingSource advertisingSource;  источник рекламы
  //  private User user;              ответственное лицо
}
