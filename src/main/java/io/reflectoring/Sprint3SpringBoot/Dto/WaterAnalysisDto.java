package io.reflectoring.Sprint3SpringBoot.Dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing a water analysis report.
 * This DTO is used to transfer water analysis data between different application layers.
 */
@Getter
@Setter
public class WaterAnalysisDto {

    /**
     * The unique identifier of the water analysis record.
     */
    public int id;

    /**
     * The concentration of radon detected in the water (measured in appropriate units).
     */
    public double radonConcentration;

    /**
     * The unique identifier of the fountain where the water sample was taken.
     */
    public int fountainId;

    /**
     * The date when the water analysis was conducted.
     */
    public LocalDate date;

    /**
     * The unique identifier of the device used for the water analysis.
     */
    public int deviceId;
}
