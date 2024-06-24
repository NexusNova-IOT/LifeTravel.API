package com.nexusnova.lifetravelapi.app.core.tours.api.rest;

import com.nexusnova.lifetravelapi.app.core.tours.api.transformation.ScheduleRangeAssembler;
import com.nexusnova.lifetravelapi.app.core.tours.domain.commands.CreateScheduleCommand;
import com.nexusnova.lifetravelapi.app.core.tours.domain.model.Schedule;
import com.nexusnova.lifetravelapi.app.core.tours.domain.queries.GetSchedulesByPackageQuery;
import com.nexusnova.lifetravelapi.app.core.tours.domain.services.ScheduleCommandService;
import com.nexusnova.lifetravelapi.app.core.tours.domain.services.ScheduleQueryService;
import com.nexusnova.lifetravelapi.app.core.tours.resources.requests.ScheduleDto;
import com.nexusnova.lifetravelapi.app.shared.constants.HeaderConstants;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.nexusnova.lifetravelapi.app.shared.messages.ConfigurationMessages.TOUR_PACKAGE_CREATED;
@RestController
@RequestMapping("/api/v1/schedules")
@Api(tags = "Api de Horarios", consumes = "application/json")
@CrossOrigin
public class ScheduleController {
    private final ScheduleCommandService scheduleCommandService;
    private final ScheduleQueryService scheduleQueryService;
    private final ScheduleRangeAssembler scheduleRangeAssembler;
    public ScheduleController(ScheduleCommandService scheduleCommandService,
                              ScheduleQueryService scheduleQueryService,
                              ScheduleRangeAssembler scheduleRangeAssembler) {
        this.scheduleCommandService = scheduleCommandService;
        this.scheduleQueryService = scheduleQueryService;
        this.scheduleRangeAssembler = scheduleRangeAssembler;
    }
    @GetMapping("/package/{packageId}")
    @Operation(summary = "Listado Por Paquete", description = "Listado de horariosn por Paquetes.")
    public List<ScheduleDto> getToursByRegion(@Parameter @PathVariable("packageId") Long packageId) {
        List<Schedule> schedules = scheduleQueryService.handle(new GetSchedulesByPackageQuery(packageId));
        return scheduleRangeAssembler.toSummariesFromData(schedules);
    }
    @PostMapping("/package/{packageId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save schedule in Tour Package")
    public List<ScheduleDto> save(@Parameter @PathVariable("packageId") Long packageId,
                                      @RequestBody @Valid List<ScheduleDto> scheduleDto,
                                      HttpServletResponse response) {
        List<Schedule> schedule =
                scheduleCommandService.handle(new CreateScheduleCommand(packageId, scheduleDto));
        response.setHeader(HeaderConstants.MESSAGES, TOUR_PACKAGE_CREATED);
        return scheduleRangeAssembler.toSummariesFromData(schedule);
    }
}
