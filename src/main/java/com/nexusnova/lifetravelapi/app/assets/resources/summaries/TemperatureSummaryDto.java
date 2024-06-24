package com.nexusnova.lifetravelapi.app.assets.resources.summaries;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Schema(name = "Temperature (Summary)")
public class TemperatureSummaryDto {
    @Schema(description = "Temperature value")
    private Double value;
    @Schema(description = "Department name")
    private String departmentName;
    @Schema(description = "Measurement date")
    private Date measurementDate;
}
