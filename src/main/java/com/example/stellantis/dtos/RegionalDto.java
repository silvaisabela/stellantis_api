package com.example.stellantis.dtos;

import com.example.stellantis.models.Vehicle;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class RegionalDto {

    private long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate operationDate;

    private List<VehicleDto> vehicles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }

    public List<VehicleDto> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDto> vehicles) {
        this.vehicles = vehicles;
    }
}
