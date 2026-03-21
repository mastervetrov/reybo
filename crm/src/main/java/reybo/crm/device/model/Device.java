package reybo.crm.device.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String malfunctions;

    private String appearance;

    private String deviceType;

    private String color;

    private String brand;

    private String model;

    private String serialNumber;

    private String equipment;

    private String password;

    private String assessedValue;

}
