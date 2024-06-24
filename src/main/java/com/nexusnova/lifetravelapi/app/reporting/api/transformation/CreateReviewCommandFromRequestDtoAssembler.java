package com.nexusnova.lifetravelapi.app.reporting.api.transformation;

import com.nexusnova.lifetravelapi.app.reporting.domain.commands.CreateReviewCommand;
import com.nexusnova.lifetravelapi.app.reporting.resources.requests.ReviewRequestDto;

public class CreateReviewCommandFromRequestDtoAssembler {
    private CreateReviewCommandFromRequestDtoAssembler() {
    }

    public static CreateReviewCommand toCommandFromDto(ReviewRequestDto requestDto) {
        return new CreateReviewCommand(requestDto);
    }
}
