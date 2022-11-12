package com.example.stellantis.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class LocationDto {

    @NotBlank(message = "District is mandatory")
    private String district;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "State is mandatory")
    @Length(min = 2, max = 2, message = "States should have only two characters")
    private String state;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
