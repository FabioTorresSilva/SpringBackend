package io.reflectoring.Sprint3SpringBoot.Dto;

import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
public class WaterAnalysisDto {

    public int id;

    public double radonConcentration;

    public int fountainId;

    public LocalDate date;

    public int deviceId;
}
