package com.nexusnova.lifetravelapi.app.assets.domain.commands;

import com.nexusnova.lifetravelapi.app.assets.resources.requests.TemperatureRequestDto;

public record RegisterTemperatureCommand(TemperatureRequestDto temperatureRequestDto) {
}
