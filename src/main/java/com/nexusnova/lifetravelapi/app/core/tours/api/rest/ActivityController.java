package com.nexusnova.lifetravelapi.app.core.tours.api.rest;

import com.nexusnova.lifetravelapi.app.core.tours.domain.model.Activity;
import com.nexusnova.lifetravelapi.app.core.tours.domain.services.ActivityQueryService;
import com.nexusnova.lifetravelapi.app.core.tours.mapper.ToursMapper;
import com.nexusnova.lifetravelapi.app.core.tours.resources.summaries.ActivitySummaryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activities")
@Tag(name="Activities Controller")
@CrossOrigin
public class ActivityController {

    private final ActivityQueryService activityQueryService;
    private final ToursMapper toursMapper;

    @Autowired
    public ActivityController(ActivityQueryService activityQueryService,
                              ToursMapper toursMapper) {
        this.activityQueryService = activityQueryService;
        this.toursMapper = toursMapper;
    }

    @GetMapping
    @Operation(summary = "Listado", description = "Listado de Actividades.")
    @ResponseStatus(HttpStatus.OK)
    public List<ActivitySummaryDto> getActivities() {
        List<Activity> activities = activityQueryService.handle();
        return toursMapper.activityToSummaryDtos(activities);
    }
}
