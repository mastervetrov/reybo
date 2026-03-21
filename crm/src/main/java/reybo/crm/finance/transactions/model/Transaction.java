package reybo.crm.finance.transactions.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
}
