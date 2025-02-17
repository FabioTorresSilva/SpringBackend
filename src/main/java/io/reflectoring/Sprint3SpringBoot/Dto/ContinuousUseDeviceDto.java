package io.reflectoring.Sprint3SpringBoot.Dto;

import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
public class ContinuousUseDeviceDto {

    public int fountainId;

    public int analysisFrequency;

    public LocalDate lastAnalysisDate;
}
