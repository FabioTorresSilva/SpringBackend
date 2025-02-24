package io.reflectoring.Sprint3SpringBoot.Dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.reflectoring.Sprint3SpringBoot.Retrofit.Config.LocalDateDeserializer;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate expirationDate;
}
