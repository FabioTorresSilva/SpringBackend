package io.reflectoring.Sprint3SpringBoot.Dto;

import io.reflectoring.Sprint3SpringBoot.Enums.SuscptibilityIndex;
import lombok.*;

@Getter
@Setter
public class FountainDto {

    public int id;

    public String description;

    public SuscptibilityIndex suscptibilityIndex;

    public int deviceId;

    public boolean isDrinkable;

    public double longitude;

    public double latitude;
}
