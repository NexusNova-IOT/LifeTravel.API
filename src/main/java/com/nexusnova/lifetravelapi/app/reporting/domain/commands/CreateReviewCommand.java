package com.nexusnova.lifetravelapi.app.reporting.domain.commands;

import com.nexusnova.lifetravelapi.app.reporting.resources.requests.ReviewRequestDto;

public record CreateReviewCommand(ReviewRequestDto requestDto) {
}
