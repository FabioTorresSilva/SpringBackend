package io.reflectoring.Sprint3SpringBoot.Dto;

import io.reflectoring.Sprint3SpringBoot.Enums.SusceptibilityIndex;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing a fountain.
 * This DTO is used to transfer fountain-related data between different application layers.
 */
public class FountainDto {

    /**
     * The unique identifier of the fountain.
     */
    public int id;

    /**
     * A brief description of the fountain.
     */
    public String description;

    /**
     * The susceptibility index of the fountain, indicating its vulnerability to contamination.
     */
    public SusceptibilityIndex susceptibilityIndex;


    /**
     * The identifier of the device associated with the fountain.
     */
    public int continuousUseDeviceId;

    /**
     * Indicates whether the water from the fountain is drinkable.
     * {@code true} if the water is safe to drink, otherwise {@code false}.
     */
    public boolean isDrinkable;

    /**
     * The geographical latitude of the fountain.
     */
    public double latitude;

    /**
     * The geographical longitude of the fountain.
     */
    public double longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SusceptibilityIndex getSusceptibilityIndex() {
        return susceptibilityIndex;
    }

    public void setSusceptibilityIndex(SusceptibilityIndex susceptibilityIndex) {
        this.susceptibilityIndex = susceptibilityIndex;
    }

    public int getContinuousUseDeviceId() {
        return continuousUseDeviceId;
    }

    public void setContinuousUseDeviceId(int continuousUseDeviceId) {
        this.continuousUseDeviceId = continuousUseDeviceId;
    }

    public boolean isDrinkable() {
        return isDrinkable;
    }

    public void setDrinkable(boolean drinkable) {
        isDrinkable = drinkable;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
