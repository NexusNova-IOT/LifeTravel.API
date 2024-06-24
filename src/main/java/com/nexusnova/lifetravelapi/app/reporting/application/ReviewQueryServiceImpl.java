package com.nexusnova.lifetravelapi.app.reporting.application;

import com.nexusnova.lifetravelapi.app.reporting.domain.model.Review;
import com.nexusnova.lifetravelapi.app.reporting.domain.queries.GetReviewByTourPackageQuery;
import com.nexusnova.lifetravelapi.app.reporting.domain.repositories.ReviewRepository;
import com.nexusnova.lifetravelapi.app.reporting.domain.services.ReviewQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> handle(GetReviewByTourPackageQuery query) {
        return reviewRepository.findByTourPackageIdAndDeletedIsFalse(query.tourPackageId());
    }

    @Override
    public List<Review> handle() {
        return reviewRepository.findByDeletedIsFalse();
    }
}
