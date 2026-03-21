package reybo.crm.store.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

/**
 * Entity store.
 * Contains store data.
 * todo дополнить
 */
@Entity
@Table(name = "store")
public class Store {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;
  // private List<Product> products;
}
