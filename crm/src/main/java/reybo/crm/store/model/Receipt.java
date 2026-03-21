package reybo.crm.store.model;

import reybo.crm.store.mapper.ReceiptMapper;
import reybo.crm.store.repository.ReceiptRepository;
import reybo.crm.store.request.ReceiptRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity receipt.
 * Contains receipt data.
 *
 * @see ReceiptRepository
 * @see ReceiptMapper
 * @see ReceiptRequest
 */
@Getter
@Setter
@Entity
@Table(name = "receipt")
public class Receipt {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "invoice_number")
  private String invoiceNumber;

  @Column(name = "payment_status")
  private String paymentStatus;
  @Column(name = "name")
  private String name;

  @Column(name = "receipt_date_time")
  private LocalDateTime receiptDateTime;

  @Column(name = "totalSum")
  private BigDecimal totalSum;

  @Column(name = "paid")
  private BigDecimal paid;

  @Column(name = "comments")
  private String comments;


//  private List<MoneyTransaction> moneyTransactionList;
//  private Location location;
//  private Store store;
//  private Provider provider;
//  private List<ReceiptItem> items;

}
