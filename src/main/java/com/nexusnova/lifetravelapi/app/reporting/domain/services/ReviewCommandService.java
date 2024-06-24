package com.nexusnova.lifetravelapi.app.reporting.domain.services;

import com.nexusnova.lifetravelapi.app.reporting.domain.commands.CreateReviewCommand;
import com.nexusnova.lifetravelapi.app.reporting.domain.commands.RemoveReviewCommand;
import com.nexusnova.lifetravelapi.app.reporting.domain.model.Review;

public interface ReviewCommandService {
    Review handle(CreateReviewCommand command);
    Review handle(RemoveReviewCommand command);
}
