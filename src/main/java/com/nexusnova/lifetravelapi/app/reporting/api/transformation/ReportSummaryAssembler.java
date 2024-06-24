package com.nexusnova.lifetravelapi.app.reporting.api.transformation;

import com.nexusnova.lifetravelapi.app.reporting.domain.model.Report;
import com.nexusnova.lifetravelapi.app.reporting.domain.model.Review;
import com.nexusnova.lifetravelapi.app.reporting.domain.repositories.ReportRepository;
import com.nexusnova.lifetravelapi.app.reporting.resources.summaries.ReportSummaryDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReportSummaryAssembler {
    private final ReportRepository reportRepository;

    public ReportSummaryAssembler(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<ReportSummaryDto> toSummaryFromData(List<Report> reports) {
        List<ReportSummaryDto> summaries = new ArrayList<>();

        for(Report report : reports) {
            summaries.add(toSummaryFromData(report));
        }
        return summaries;
    }

    public ReportSummaryDto toSummaryFromData(Report report) {
        ReportSummaryDto summary = new ReportSummaryDto();

        summary.setReportId(report.getId());
        summary.setAgencyBusinessName(report.getAgency().getLegalName());
        summary.setAgencyId(report.getAgency().getId());
        summary.setNumberOfReviews(report.getReviews().size());
        float averageRating = 0.0f;
        for (Review review : report.getReviews()) {
            averageRating += review.getRating();
        }
        List<Long> tourPackageIds = new ArrayList<>();
        for (Review review : report.getReviews()) {
            tourPackageIds.add(review.getTourPackage().getId());
        }
        summary.setTourPackageIds(tourPackageIds);

        if (!report.getReviews().isEmpty()) {
            averageRating = averageRating / report.getReviews().size();
        }
        summary.setAverageRating(averageRating);
        summary.setContent(report.getContent());
        summary.setAiRecommendation(report.getAiRecommendation());
        summary.setGeneratedDate(report.getCreatedDate());
        return summary;
    }
}
