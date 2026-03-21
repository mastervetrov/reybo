package reybo.crm.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Entity company contractor.
 * This is child entity of {@link Contractor}
 */
@Entity
@Table(name = "company_contractor")
public class CompanyContractor extends Contractor {

  // ДАННЫЕ ПО КОМПАНИИ
  private String inn; // ИНН
  private String kpp; // КПП
  private String director; // директор
  private String contract; // договор

  // БАНКОВСКИЕ РЕКВИЗИТЫ
  private String bankName; // наименование банка
  private String bik; // БИК
  private String swift; // swift
  private String correspondenceAccount; // кор. счет
  private String settlementAccount; // рассчетчный счет

}
