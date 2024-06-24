package com.nexusnova.lifetravelapi.app.reporting.resources.summaries;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Schema(description="Review (Summary)")
public class ReviewSummaryDto {
    @Schema(description="Review ID")
    private Long reviewId;

    @Schema(description="Tourist ID")
    private Long touristId;

    @Schema(description="Tourist Name")
    private String touristName;

    @Schema(description="Tour Package ID")
    private Long tourPackageId;

    @Schema(description="Tour Package Name")
    private String tourPackageName;

    @Schema(description="Rating")
    private Integer rating;

    @Schema(description="Review Text")
    private String reviewText;

    @Schema(description="Review Date")
    private Date reviewDate;
}
