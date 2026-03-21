package reybo.crm.finance.transactions.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransactionRequest {

    private UUID id;

    private String title;
}
