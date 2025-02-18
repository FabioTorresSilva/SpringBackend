package io.reflectoring.Sprint3SpringBoot.Dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing a device.
 * This DTO is used to transfer device-related data between different application layers.
 */
@Getter
@Setter
public class DeviceDto {

    /**
     * The unique identifier of the device.
     */
    public int id;

    /**
     * The model name or type of the device.
     */
    public String model;

    /**
     * The serial number of the device, used for identification.
     */
    public String serialNumber;

    /**
     * The expiration date of the device, indicating when it should be replaced or serviced.
     */
    public LocalDate expirationDate;
}
