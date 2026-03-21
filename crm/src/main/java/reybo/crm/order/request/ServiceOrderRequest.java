package reybo.crm.order.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ServiceOrderRequest {

    private UUID id;

    // Contractor
    private String surname; // Фамилия
    private String firstname; // Имя
    private String lastname; // Отчество

    private String phone;
    private String advertisingSource;

    // ABOUT DEVICES
    private String serialNumber;
    private String deviceType;

    private String brand;
    private String model;

    private String color;
    private String equipment;

    private String malfunctions;
    private String assessedValue;

    private String appearance;
    private String password;

    // PARAMETERS
    private String agreedServices;
    private BigDecimal agreedPrice;

    private String currentMaster;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm[:ss]")
    private LocalDateTime deadline;

    private String createdManager;
    private String currentManager;
    private String issuingManager;

    private BigDecimal prepayment;

    private Boolean urgently = false;
    private String comment = "";

}
