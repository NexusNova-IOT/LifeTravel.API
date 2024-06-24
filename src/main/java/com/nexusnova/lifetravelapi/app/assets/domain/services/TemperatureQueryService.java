package com.nexusnova.lifetravelapi.app.assets.domain.services;

import com.nexusnova.lifetravelapi.app.assets.domain.model.Temperature;

import java.util.List;

public interface TemperatureQueryService {
    List<Temperature> getTemperatures();
    List<Temperature> getTemperaturesByDepartmentId(Long departmentId);
    Temperature getTemperatureById(Long id);
}
