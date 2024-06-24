package com.nexusnova.lifetravelapi.app.assets.domain.commands;

import com.nexusnova.lifetravelapi.app.assets.resources.requests.TemperatureRequestDto;

import java.util.List;

public record RegisterMultipleTemperatureCommand(List<TemperatureRequestDto> temperatureRequestDtos) {
}
