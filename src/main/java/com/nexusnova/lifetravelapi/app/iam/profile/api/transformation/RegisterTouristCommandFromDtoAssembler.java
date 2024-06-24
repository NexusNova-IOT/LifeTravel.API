package com.nexusnova.lifetravelapi.app.iam.profile.api.transformation;

import com.nexusnova.lifetravelapi.app.iam.profile.domain.commands.CreateTouristCommand;
import com.nexusnova.lifetravelapi.app.iam.profile.resources.requests.TouristRequestDto;

public class RegisterTouristCommandFromDtoAssembler {
    private RegisterTouristCommandFromDtoAssembler() {
    }
    public static CreateTouristCommand toCommandFromDto(String id, TouristRequestDto requestDto){
        return new CreateTouristCommand(id, requestDto);
    }
}
