package com.nexusnova.lifetravelapi.app.assets.domain.repositories;

import com.nexusnova.lifetravelapi.app.assets.domain.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    List<Temperature> findByDepartmentId(Long departmentId);
}
