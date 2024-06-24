package com.nexusnova.lifetravelapi.app.reporting.resources.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description="Report (Request)")
public class ReportRequestDto {
    @Schema(description="Agency ID")
    private Long agencyId;
    @Schema(description="Tour Package IDs")
    private List<Long> tourPackageIds;
}
