package com.example.stellantis.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class EnvironmentDto {

    private long id;

    private LocationDto location;

    private int temperature;

    @Min(value = 0, message = "Ar quality is a percent value it should be between 0 and 100")
    @Max(value = 100)
    private int arQuality;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getArQuality() {
        return arQuality;
    }

    public void setArQuality(int arQuality) {
        this.arQuality = arQuality;
    }
}
