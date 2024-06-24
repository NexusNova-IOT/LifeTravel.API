package com.nexusnova.lifetravelapi.app.assets.domain.services;

import com.nexusnova.lifetravelapi.app.assets.domain.commands.RegisterMultipleTemperatureCommand;
import com.nexusnova.lifetravelapi.app.assets.domain.commands.RegisterTemperatureCommand;
import com.nexusnova.lifetravelapi.app.assets.domain.model.Temperature;

import java.util.List;

public interface TemperatureCommandService {
    Temperature handle(RegisterTemperatureCommand command);
    Temperature addTemperature(Temperature temperature);
    Temperature updateTemperature(Temperature temperature);
    List<Temperature> handle(RegisterMultipleTemperatureCommand command);
    List<Temperature> addTemperatures(List<Temperature> temperatures);
}
