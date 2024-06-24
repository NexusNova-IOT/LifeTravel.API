package com.nexusnova.lifetravelapi.app.reporting.application;

import com.nexusnova.lifetravelapi.app.core.tours.domain.model.TourPackage;
import com.nexusnova.lifetravelapi.app.core.tours.domain.services.OpenAIClientService;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.model.Agency;
import com.nexusnova.lifetravelapi.app.reporting.domain.commands.GenerateReportCommand;
import com.nexusnova.lifetravelapi.app.reporting.domain.commands.RemoveReportCommand;
import com.nexusnova.lifetravelapi.app.reporting.domain.model.Report;
import com.nexusnova.lifetravelapi.app.reporting.domain.model.Review;
import com.nexusnova.lifetravelapi.app.reporting.domain.repositories.ReportRepository;
import com.nexusnova.lifetravelapi.app.reporting.domain.services.ReportCommandService;
import com.nexusnova.lifetravelapi.app.shared.exceptions.ResourceNotFoundException;
import com.nexusnova.lifetravelapi.app.shared.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReportCommandServiceImpl implements ReportCommandService {
    private final ReportRepository reportRepository;
    private final ValidationUtil validationUtil;
    private final OpenAIClientService openAIClientService;

    public ReportCommandServiceImpl(ReportRepository reportRepository, ValidationUtil validationUtil, OpenAIClientService openAIClientService) {
        this.reportRepository = reportRepository;
        this.validationUtil = validationUtil;
        this.openAIClientService = openAIClientService;
    }

    @Override
    public Report handle(GenerateReportCommand command) {
        Report report = new Report();
        Agency agency = validationUtil.findAgencyById(command.requestDto().getAgencyId());
        report.setAgency(agency);
        List<TourPackage> tourPackages = validationUtil.findTourPackagesByIds(command.requestDto().getTourPackageIds());
        Set<Review> reviews = new HashSet<>();
        for (TourPackage tourPackage : tourPackages) {
            reviews.addAll(tourPackage.getReviews());
        }
        report.setReviews(reviews);
        report.setContent(generateReportSummary(tourPackages));
        report.setAiRecommendation(getChatResponse(report.getContent()));
        report.setDeleted(false);
        return reportRepository.save(report);
    }

    @Override
    public Report handle(RemoveReportCommand command) {
        Report report = reportRepository.findById(command.id()).orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + command.id()));
        report.setDeleted(true);
        return reportRepository.save(report);
    }

    public String generateReportSummary(List<TourPackage> tourPackages) {
        StringBuilder reportSummary = new StringBuilder();

        for (TourPackage tourPackage : tourPackages) {
            String tourPackageName = tourPackage.getTitle();

            if (tourPackage.getReviews().isEmpty()) {
                reportSummary.append("No reviews available for Tour Package: ").append(tourPackageName).append("\n");
                continue;
            }

            Set<Review> reviews = new HashSet<>(tourPackage.getReviews());
            reportSummary.append("Tour Package: ").append(tourPackageName).append("\n");

            for (Review review : reviews) {
                reportSummary.append("Rating: ").append(review.getRating()).append("\n");
                reportSummary.append("Review Text: ").append(review.getReviewText()).append("\n");
                reportSummary.append("\n");
            }

            reportSummary.append("\n");
        }

        return reportSummary.toString();
    }

    private String getChatResponse(String summary) {
        String basePrompt = """
            I will send you a report from a tourism agency. I would like you to analyze it and based on the reviews provided by each TourPackage. Provide a report or recommendation for the tourist agency. You must include the bad points, those to improve and those that stand out, as long as it is applicable. If no tour packages have reviews, respond politely stating that there are no reviews. Make sure the text you send if there are no reviews does not exceed 30 words. And if there are reviews, they should not exceed 200 words. Do not respond as in the third person, instead provide the recommendation as if you were conversing with the agency. Be concise, precise and useful here is the report: %s
            """;
        String formattedPrompt = String.format(basePrompt, summary);
        return openAIClientService.getSimpleChatResponse(formattedPrompt);
    }
}
