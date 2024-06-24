package com.nexusnova.lifetravelapi.app.reporting.resources.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="Review (Request)")
public class ReviewRequestDto {
    @Schema(description="Tourist ID")
    private Long touristId;

    @Schema(description="Tour Package ID")
    private Long tourPackageId;

    @Schema(description="Rating")
    private Integer rating;

    @Schema(description="Review Text")
    private String reviewText;
}
