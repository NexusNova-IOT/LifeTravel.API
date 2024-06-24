package com.nexusnova.lifetravelapi.app.assets.api.transformation;

import com.nexusnova.lifetravelapi.app.assets.domain.commands.RegisterTemperatureCommand;
import com.nexusnova.lifetravelapi.app.assets.resources.requests.TemperatureRequestDto;

public class RegisterTemperatureCommandFromRequestDtoAssembler {
    private RegisterTemperatureCommandFromRequestDtoAssembler() {
    }

    public static RegisterTemperatureCommand toCommandFromDto(TemperatureRequestDto requestDto){
        return new RegisterTemperatureCommand(requestDto);
    }
}
