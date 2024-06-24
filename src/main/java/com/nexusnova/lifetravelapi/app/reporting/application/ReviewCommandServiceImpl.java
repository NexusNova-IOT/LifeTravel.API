package com.nexusnova.lifetravelapi.app.reporting.application;

import com.nexusnova.lifetravelapi.app.reporting.domain.commands.CreateReviewCommand;
import com.nexusnova.lifetravelapi.app.reporting.domain.commands.RemoveReviewCommand;
import com.nexusnova.lifetravelapi.app.reporting.domain.model.Review;
import com.nexusnova.lifetravelapi.app.reporting.domain.repositories.ReviewRepository;
import com.nexusnova.lifetravelapi.app.reporting.domain.services.ReviewCommandService;
import com.nexusnova.lifetravelapi.app.shared.exceptions.ResourceNotFoundException;
import com.nexusnova.lifetravelapi.app.shared.util.ValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final ValidationUtil validationUtil;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository, ValidationUtil validationUtil) {
        this.reviewRepository = reviewRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public Review handle(CreateReviewCommand command) {
        Review review = new Review();
        review.setTourist(validationUtil.findTouristById(command.requestDto().getTouristId()));
        review.setTourPackage(validationUtil.findTourPackageById(command.requestDto().getTourPackageId()));
        review.setRating(command.requestDto().getRating());
        review.setReviewText(command.requestDto().getReviewText());
        review.setDeleted(false);
        return reviewRepository.save(review);
    }

    @Override
    public Review handle(RemoveReviewCommand command) {
        Review review = reviewRepository.findById(command.id()).orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + command.id()));
        review.setDeleted(true);
        return reviewRepository.save(review);
    }
}
