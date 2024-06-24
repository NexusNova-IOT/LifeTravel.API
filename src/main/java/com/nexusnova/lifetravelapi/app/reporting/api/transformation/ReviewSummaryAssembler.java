package com.nexusnova.lifetravelapi.app.reporting.api.transformation;

import com.nexusnova.lifetravelapi.app.reporting.domain.model.Review;
import com.nexusnova.lifetravelapi.app.reporting.resources.summaries.ReviewSummaryDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewSummaryAssembler {
    public ReviewSummaryDto toSummaryFromData(Review review) {
        ReviewSummaryDto summary = new ReviewSummaryDto();

        summary.setReviewId(review.getId());
        summary.setTouristId(review.getTourist().getId());
        summary.setTouristName(review.getTourist().getName());
        summary.setTourPackageId(review.getTourPackage().getId());
        summary.setTourPackageName(review.getTourPackage().getTitle());
        summary.setRating(review.getRating());
        summary.setReviewText(review.getReviewText());
        summary.setReviewDate(review.getCreatedDate());

        return summary;
    }

    public List<ReviewSummaryDto> toSummaryFromData(List<Review> reviews) {
        List<ReviewSummaryDto> summaries = new ArrayList<>();

        for(Review review : reviews) {
            summaries.add(toSummaryFromData(review));
        }
        return summaries;
    }
}
