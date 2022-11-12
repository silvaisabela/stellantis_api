package com.example.stellantis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class VehicleDto {

    private long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long regionalId;

    @NotBlank(message = "Brand is mandatory")
    private String brand;

    @NotBlank(message = "Model is mandatory")
    private String model;

    @NotBlank(message = "License plate is mandatory")
    private String licensePlate;

    @Min(value = 0)
    private int mileageTraveled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getMileageTraveled() {
        return mileageTraveled;
    }

    public void setMileageTraveled(int mileageTraveled) {
        this.mileageTraveled = mileageTraveled;
    }

    public long getRegionalId() {
        return regionalId;
    }

    public void setRegionalId(long regionalId) {
        this.regionalId = regionalId;
    }
}