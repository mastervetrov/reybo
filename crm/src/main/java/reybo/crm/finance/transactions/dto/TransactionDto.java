package reybo.crm.finance.transactions.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransactionDto {

    private UUID id;

    private String title;

}
