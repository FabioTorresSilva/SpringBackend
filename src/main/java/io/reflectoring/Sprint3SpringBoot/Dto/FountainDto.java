package io.reflectoring.Sprint3SpringBoot.Dto;

import io.reflectoring.Sprint3SpringBoot.Enums.SuscptibilityIndex;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing a fountain.
 * This DTO is used to transfer fountain-related data between different application layers.
 */
@Getter
@Setter
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
    public SuscptibilityIndex suscptibilityIndex;

    /**
     * The identifier of the device associated with the fountain.
     */
    public int deviceId;

    /**
     * Indicates whether the water from the fountain is drinkable.
     * {@code true} if the water is safe to drink, otherwise {@code false}.
     */
    public boolean isDrinkable;

    /**
     * The geographical longitude of the fountain.
     */
    public double longitude;

    /**
     * The geographical latitude of the fountain.
     */
    public double latitude;
}
