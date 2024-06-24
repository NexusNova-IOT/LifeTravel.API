package com.nexusnova.lifetravelapi.app.assets.resources.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Temperature (Request)")
public class TemperatureRequestDto {
    @Schema(description = "Temperature")
    private double value;

    @Schema(description = "Department Id")
    private long departmentId;
}
