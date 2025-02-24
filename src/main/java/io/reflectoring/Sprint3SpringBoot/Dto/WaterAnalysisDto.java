package io.reflectoring.Sprint3SpringBoot.Dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.reflectoring.Sprint3SpringBoot.Retrofit.Config.LocalDateDeserializer;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing a water analysis report.
 * This DTO is used to transfer water analysis data between different application layers.
 */
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate date;

    /**
     * The unique identifier of the device used for the water analysis.
     */
    public int deviceId;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getFountainId() {
        return fountainId;
    }

    public void setFountainId(int fountainId) {
        this.fountainId = fountainId;
    }

    public double getRadonConcentration() {
        return radonConcentration;
    }

    public void setRadonConcentration(double radonConcentration) {
        this.radonConcentration = radonConcentration;
    }
}
