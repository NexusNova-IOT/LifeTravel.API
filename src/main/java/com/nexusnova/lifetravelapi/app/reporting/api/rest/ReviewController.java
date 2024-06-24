package com.nexusnova.lifetravelapi.app.reporting.api.rest;

import com.nexusnova.lifetravelapi.app.reporting.api.transformation.CreateReviewCommandFromRequestDtoAssembler;
import com.nexusnova.lifetravelapi.app.reporting.api.transformation.ReviewSummaryAssembler;
import com.nexusnova.lifetravelapi.app.reporting.domain.commands.CreateReviewCommand;
import com.nexusnova.lifetravelapi.app.reporting.domain.commands.RemoveReviewCommand;
import com.nexusnova.lifetravelapi.app.reporting.domain.model.Review;
import com.nexusnova.lifetravelapi.app.reporting.domain.queries.GetReviewByTourPackageQuery;
import com.nexusnova.lifetravelapi.app.reporting.domain.services.ReviewCommandService;
import com.nexusnova.lifetravelapi.app.reporting.domain.services.ReviewQueryService;
import com.nexusnova.lifetravelapi.app.reporting.resources.requests.ReviewRequestDto;
import com.nexusnova.lifetravelapi.app.reporting.resources.summaries.ReviewSummaryDto;
import com.nexusnova.lifetravelapi.app.shared.constants.HeaderConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nexusnova.lifetravelapi.app.shared.messages.ConfigurationMessages.REVIEW_CREATED;

@RestController
@RequestMapping("/api/v1/reviews")
@Tag(name="Review Controller")
@CrossOrigin
public class ReviewController {
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;
    private final ReviewSummaryAssembler reviewSummaryAssembler;

    @Autowired
    public ReviewController(ReviewCommandService reviewCommandService, ReviewQueryService reviewQueryService, ReviewSummaryAssembler reviewSummaryAssembler) {
        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
        this.reviewSummaryAssembler = reviewSummaryAssembler;
    }

    @GetMapping("/")
    @Operation(summary = "Get all reviews", description = "Get all reviews")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewSummaryDto> getAllReviews() {
        List<Review> reviews = reviewQueryService.handle();
        return reviewSummaryAssembler.toSummaryFromData(reviews);
    }

    @GetMapping("tour-package/{tourPackageId}")
    @Operation(summary = "Get reviews by tour package id", description = "Get reviews by tour package id")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewSummaryDto> getReviewsByTourPackageId(@Parameter @PathVariable("tourPackageId") Long tourPackageId) {
        List<Review> reviews = reviewQueryService.handle(new GetReviewByTourPackageQuery(tourPackageId));
        return reviewSummaryAssembler.toSummaryFromData(reviews);
    }

    @PostMapping("/")
    @Operation(summary = "Create review", description = "Create a new review")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewSummaryDto createReview(@RequestBody @Valid ReviewRequestDto reviewRequestDto, HttpServletResponse response) {
        Review review = reviewCommandService.handle(CreateReviewCommandFromRequestDtoAssembler.toCommandFromDto(reviewRequestDto));
        response.setHeader(HeaderConstants.MESSAGES, REVIEW_CREATED);
        return reviewSummaryAssembler.toSummaryFromData(review);
    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = "Delete review", description = "Delete a review by id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ReviewSummaryDto deleteReview(@Parameter @PathVariable("reviewId") Long reviewId) {
        Review review = reviewCommandService.handle(new RemoveReviewCommand(reviewId));
        return reviewSummaryAssembler.toSummaryFromData(review);
    }

}
