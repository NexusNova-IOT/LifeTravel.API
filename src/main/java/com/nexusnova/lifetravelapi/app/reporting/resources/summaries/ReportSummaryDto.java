package com.nexusnova.lifetravelapi.app.reporting.resources.summaries;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Schema(description = "Report (Summary)")
public class ReportSummaryDto {
        @Schema(description = "Report ID")
        private Long reportId;

        @Schema(description = "Agency business name")
        private String agencyBusinessName;

        @Schema(description = "Agency ID")
        private Long agencyId;

        @Schema(description = "TourPackage IDs")
        private List<Long> tourPackageIds;

        @Schema(description = "Number of Reviews")
        private Integer numberOfReviews;

        @Schema(description = "Average Rating")
        private Float averageRating;

        @Schema(description = "Report Content")
        private String content;

        @Schema(description = "AI Recommendation")
        private String aiRecommendation;

        @Schema(description = "Generated date")
        private Date generatedDate;
}
