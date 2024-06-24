package com.nexusnova.lifetravelapi.app.assets.mapper;

import com.nexusnova.lifetravelapi.app.assets.domain.model.Temperature;
import com.nexusnova.lifetravelapi.app.assets.domain.model.TrackingWearable;
import com.nexusnova.lifetravelapi.app.assets.domain.model.WeightBalance;
import com.nexusnova.lifetravelapi.app.assets.resources.summaries.TemperatureSummaryDto;
import com.nexusnova.lifetravelapi.app.assets.resources.summaries.TrackingWereableSummayDto;
import com.nexusnova.lifetravelapi.app.assets.resources.summaries.WeightBalanceSummaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOTMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),

    })
    TrackingWereableSummayDto gpsToSummaryDto(TrackingWearable entity);
    List<TrackingWereableSummayDto> gpsToSummaryDtos(List<TrackingWearable> entities);

    @Mappings({
            @Mapping(target = "actualWeight", source = "entity.weight"),
            @Mapping(target = "maxWeight", source = "entity.vehicle.weight")
    })
    WeightBalanceSummaryDto balanceToSummaryDto(WeightBalance entity);
    List<WeightBalanceSummaryDto> balanceToSummaryDtos(List<WeightBalance> entities);

    @Mapping(target = "value", source = "entity.value")
    @Mapping(target = "departmentName", source = "entity.department.name")
    @Mapping(target = "measurementDate", source = "entity.measuredAt")
    TemperatureSummaryDto temperatureToSummaryDto(Temperature entity);
    List<TemperatureSummaryDto> temperatureToSummaryDtos(List<Temperature> entities);
}
