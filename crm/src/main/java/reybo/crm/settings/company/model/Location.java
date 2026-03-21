package reybo.crm.settings.company.model;

import reybo.crm.settings.company.repository.LocationRepository;
import reybo.crm.settings.company.request.LocationRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Entity location.
 * Contains location data.
 *
 * @see LocationRequest
 * @see LocationService
 * @see LocationRepository
 */
@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "color")
  private String color;

  @Column(name = "address")
  private String address;

  @Column(name = "phone_number")
  private String phoneNumber;

}
