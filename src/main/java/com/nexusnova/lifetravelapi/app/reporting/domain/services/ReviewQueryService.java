package com.nexusnova.lifetravelapi.app.reporting.domain.services;

import com.nexusnova.lifetravelapi.app.reporting.domain.model.Review;
import com.nexusnova.lifetravelapi.app.reporting.domain.queries.GetReviewByTourPackageQuery;

import java.util.List;

public interface ReviewQueryService {
    List<Review> handle(GetReviewByTourPackageQuery query);
    List<Review> handle();
}
