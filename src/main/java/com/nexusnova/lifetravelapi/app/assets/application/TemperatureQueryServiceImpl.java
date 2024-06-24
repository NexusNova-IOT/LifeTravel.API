package com.nexusnova.lifetravelapi.app.assets.application;

import com.nexusnova.lifetravelapi.app.assets.domain.model.Temperature;
import com.nexusnova.lifetravelapi.app.assets.domain.repositories.TemperatureRepository;
import com.nexusnova.lifetravelapi.app.assets.domain.services.TemperatureQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureQueryServiceImpl implements TemperatureQueryService {

    private final TemperatureRepository temperatureRepository;

    public TemperatureQueryServiceImpl(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public List<Temperature> getTemperatures() {
        return temperatureRepository.findAll();
    }

    @Override
    public List<Temperature> getTemperaturesByDepartmentId(Long departmentId) {
        return temperatureRepository.findByDepartmentId(departmentId);
    }

    @Override
    public Temperature getTemperatureById(Long id) {
        return temperatureRepository.findById(id).orElse(null);
    }
}
