package io.reflectoring.Sprint3SpringBoot.Dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing a continuous use device.
 * This DTO contains information related to a device that requires periodic analysis.
 */
@Getter
@Setter
public class ContinuousUseDeviceDto {

    /**
     * The unique identifier of the associated fountain.
     */
    public int fountainId;

    /**
     * The frequency (in days) at which the device should be analyzed.
     */
    public int analysisFrequency;

    /**
     * The date when the last analysis was conducted on the device.
     */
    public LocalDate lastAnalysisDate;
}
