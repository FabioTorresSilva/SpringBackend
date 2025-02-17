package io.reflectoring.Sprint3SpringBoot.Dto;

import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
public class DeviceDto {

    public int id;

    public String model;

    public String serialNumber;

    public LocalDate expirationDate;
}
