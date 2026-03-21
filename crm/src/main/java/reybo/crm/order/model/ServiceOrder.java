package reybo.crm.order.model;

import reybo.crm.common.api.entity.CrmEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "service_order")
@Getter
@Setter
/*
    Сущность для создания заказов не связаных с интернет-магазином.
    Например для создания заказа на ремонт и создания заказа записи на ремонт. Тип закаа ограничивается только реализацией OrderType.
 */
public class ServiceOrder implements CrmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    private LocalDateTime deadline;

    private String createdManager;
    private String currentManager;
    private String issuingManager;

    private BigDecimal prepayment;

    private Boolean urgently = false;
    private String comment = "";

}
