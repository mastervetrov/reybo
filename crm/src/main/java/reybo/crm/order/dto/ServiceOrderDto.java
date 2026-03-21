package reybo.crm.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ServiceOrderDto {

    private UUID id;

    private String orderType;

    private UUID masterId;

    private BigDecimal prepayment;

    private String comment = "";

    private Boolean urgently;

    private UUID managerId;

    private BigDecimal approximatePrice;

    private LocalDateTime deadline;

}
