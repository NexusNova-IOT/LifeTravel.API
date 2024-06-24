package com.nexusnova.lifetravelapi.app.assets.api.transformation;

import com.nexusnova.lifetravelapi.app.assets.domain.commands.RegisterMultipleTemperatureCommand;
import com.nexusnova.lifetravelapi.app.assets.resources.requests.TemperatureRequestDto;

import java.util.List;

public class RegisterMultipleTemperatureCommandFromRequestDtoAssembler {
    private RegisterMultipleTemperatureCommandFromRequestDtoAssembler() {
    }

    public static RegisterMultipleTemperatureCommand toCommandFromDto(List<TemperatureRequestDto> requestDto){
        return new RegisterMultipleTemperatureCommand(requestDto);
    }
}
