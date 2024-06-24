package com.nexusnova.lifetravelapi.app.iam.profile.api.transformation;

import com.nexusnova.lifetravelapi.app.iam.profile.domain.commands.CreateAgencyCommand;
import com.nexusnova.lifetravelapi.app.iam.profile.resources.requests.AgencyRequestDto;

public class RegisterAgencyCommandFromDtoAssembler {
    private RegisterAgencyCommandFromDtoAssembler() {
    }
    public static CreateAgencyCommand toCommandFromDto(String id, AgencyRequestDto requestDto){
        return new CreateAgencyCommand(id, requestDto);
    }
}
