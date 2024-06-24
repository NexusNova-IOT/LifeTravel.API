package com.nexusnova.lifetravelapi.app.assets.application;

import com.nexusnova.lifetravelapi.app.assets.domain.commands.RegisterMultipleTemperatureCommand;
import com.nexusnova.lifetravelapi.app.assets.domain.commands.RegisterTemperatureCommand;
import com.nexusnova.lifetravelapi.app.assets.domain.model.Temperature;
import com.nexusnova.lifetravelapi.app.assets.domain.repositories.TemperatureRepository;
import com.nexusnova.lifetravelapi.app.assets.domain.services.TemperatureCommandService;
import com.nexusnova.lifetravelapi.app.core.tours.domain.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TemperatureCommandServiceImpl implements TemperatureCommandService {

    private final TemperatureRepository temperatureRepository;
    private final DepartmentRepository departmentRepository;

    public TemperatureCommandServiceImpl(TemperatureRepository temperatureRepository, DepartmentRepository departmentRepository) {
        this.temperatureRepository = temperatureRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Temperature handle(RegisterTemperatureCommand command) {
        Temperature temperature = new Temperature();
        temperature.setValue(command.temperatureRequestDto().getValue());
        temperature.setDepartment(departmentRepository.findById(command.temperatureRequestDto().getDepartmentId()).orElse(null));
        temperature.setMeasuredAt(new Date());
        return temperatureRepository.save(temperature);
    }

    @Override
    public Temperature addTemperature(Temperature temperature) {
        return temperatureRepository.save(temperature);
    }

    @Override
    public Temperature updateTemperature(Temperature temperature) {
        return temperatureRepository.save(temperature);
    }

    @Override
    public List<Temperature> handle(RegisterMultipleTemperatureCommand command) {
        return temperatureRepository.saveAll(command.temperatureRequestDtos().stream().map(dto -> {
            Temperature temperature = new Temperature();
            temperature.setValue(dto.getValue());
            temperature.setDepartment(departmentRepository.findById(dto.getDepartmentId()).orElse(null));
            temperature.setMeasuredAt(new Date());
            return temperature;
        }).toList());
    }

    public List<Temperature> addTemperatures(List<Temperature> temperatures) {
        return temperatureRepository.saveAll(temperatures);
    }
}
