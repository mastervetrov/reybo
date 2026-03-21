package reybo.crm.order.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "service_order_form")
@Getter
@Setter
public class ServiceOrderForm {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Contractor
    private String surname = "";// Фамилия
    private String firstname = ""; // Имя
    private String lastname = ""; // Отчество

    private String phone = "";
    private String advertisingSource = "";

    // ABOUT DEVICES
    private String serialNumber = "";
    private String deviceType = "";

    private String brand = "";
    private String model = "";

    private String color = "";
    private String equipment = "";

    private String malfunctions = "";
    private String assessedValue = "";

    private String appearance = "";
    private String password = "";

    // PARAMETERS
    private String agreedServices = "";
    private BigDecimal agreedPrice = new BigDecimal(0L);

    private String currentMaster = "";
    private LocalDateTime deadline = LocalDateTime.now();

    private String createdManager = "";
    private String currentManager = "";
    private String issuingManager = "";

    private BigDecimal prepayment = new BigDecimal(0L);

    private Boolean urgently = false;
    private String comment = "";


    public Map<String, Object> getParametersMap() {
        Map<String, Object> parametersMap = new HashMap<>();

        // CONTRACTOR
        parametersMap.put("surname", surname);
        parametersMap.put("firstname", firstname);
        parametersMap.put("lastname", lastname);

        parametersMap.put("phone", phone);
        parametersMap.put("advertisingSource", advertisingSource);

        // ABOUT DEVICES
        parametersMap.put("serialNumber", serialNumber);
        parametersMap.put("deviceType", deviceType);
        parametersMap.put("brand", brand);
        parametersMap.put("model", model);
        parametersMap.put("color", color);
        parametersMap.put("equipment", equipment);
        parametersMap.put("malfunctions", malfunctions);
        parametersMap.put("assessedValue", assessedValue);
        parametersMap.put("appearance", appearance);
        parametersMap.put("password", password);

        // PARAMETERS
        parametersMap.put("agreedServices", agreedServices);
        parametersMap.put("agreedPrice", agreedPrice);
        parametersMap.put("currentMaster", currentMaster);
        parametersMap.put("deadline", deadline);
        parametersMap.put("createdManager", createdManager);
        parametersMap.put("currentManager", currentManager);
        parametersMap.put("issuingManager", issuingManager);
        parametersMap.put("prepayment", prepayment);
        parametersMap.put("urgently", urgently);
        parametersMap.put("comment", comment);

        // ID (обычно оставляют в конце или начале)
        parametersMap.put("id", id);

        return parametersMap;
    }

}
