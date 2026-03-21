package reybo.crm.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Entity private contractor.
 * This is child entity of {@link Contractor}
 */
@Entity
@Table(name = "private_contractor")
public class PrivateContractor extends Contractor {
  private String passportSerialNumber;
  private String issuedByWhom;
  private String dateOfIssue;
  private String unitCode;
  private String dateBirthday;
  private String placeOfBirth;
  private String taxpayerIdentificationNumber;
}
