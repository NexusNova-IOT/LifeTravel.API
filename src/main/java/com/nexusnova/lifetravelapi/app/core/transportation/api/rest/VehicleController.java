package com.nexusnova.lifetravelapi.app.core.transportation.api.rest;

import com.nexusnova.lifetravelapi.app.core.transportation.domain.commands.*;
import com.nexusnova.lifetravelapi.app.core.transportation.domain.enums.VehicleStatus;
import com.nexusnova.lifetravelapi.app.core.transportation.domain.model.Vehicle;
import com.nexusnova.lifetravelapi.app.core.transportation.domain.queries.GetVehicleByIdQuery;
import com.nexusnova.lifetravelapi.app.core.transportation.domain.queries.GetVehiclesByAgencyUserIdAndStatusQuery;
import com.nexusnova.lifetravelapi.app.core.transportation.domain.queries.GetVehiclesByTourPackageQuery;
import com.nexusnova.lifetravelapi.app.core.transportation.domain.services.VehicleCommandService;
import com.nexusnova.lifetravelapi.app.core.transportation.domain.services.VehicleQueryService;
import com.nexusnova.lifetravelapi.app.core.transportation.resources.requests.VehicleRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Transportation Controller")
@CrossOrigin
public class VehicleController {
    private final VehicleQueryService vehicleQueryService;
    private final VehicleCommandService vehicleCommandService;

    public VehicleController(VehicleQueryService vehicleQueryService,
                             VehicleCommandService vehicleCommandService) {
        this.vehicleQueryService = vehicleQueryService;
        this.vehicleCommandService = vehicleCommandService;
    }
    @PostMapping("/create")
    @Operation(summary = "Create Vehicle")
    public Vehicle createVehicle(@RequestBody VehicleRequestDto vehicle) {
        return vehicleCommandService.handle(new CreateVehicleCommand(vehicle));
    }
    @GetMapping("{vehicleId}")
    @Operation(summary = "Get Vehicle by Id")
    public Vehicle getVehicleById(@PathVariable("vehicleId") Long vehicleId) {
        return vehicleQueryService.handle(new GetVehicleByIdQuery(vehicleId));
    }
    @GetMapping("/all-vehicles-by-agency-user-id-and-status/{agencyUserId}/{status}")
    @Operation(summary = "List existing Vehicles by Agency User Id and Status")
    public List<Vehicle> getAllVehiclesByAgencyUserIdAndStatus(@PathVariable("agencyUserId") String agencyUserId,
                                                               @PathVariable("status") VehicleStatus status) {
        return vehicleQueryService.handle(new GetVehiclesByAgencyUserIdAndStatusQuery(agencyUserId, status));
    }
    @PutMapping("/img/{vehicleId}")
    @Operation(summary = "Modify Vehicle Image")
    public Vehicle modifyVehicleImg(@PathVariable("vehicleId") Long vehicleId,
                                    @RequestBody String img) {
        return vehicleCommandService.handle(new ModifyImgVehicleCommand(vehicleId, img));
    }
    @PutMapping("/modify/{vehicleId}")
    @Operation(summary = "Modify Vehicle")
    public Vehicle modifyVehicle(@PathVariable("vehicleId") Long vehicleId,
                                 @RequestBody VehicleRequestDto vehicle) {
        return vehicleCommandService.handle(new ModifyVehicleCommand(vehicleId, vehicle));
    }
    @GetMapping("/all-vehicles-by-tour-package/{tourPackageId}")
    @Operation(summary = "List existing Vehicles by Tour Package Id")
    public List<Vehicle> getAllVehiclesByTourPackage(@PathVariable("tourPackageId") Long tourPackageId) {
        return vehicleQueryService.handle(new GetVehiclesByTourPackageQuery(tourPackageId));
    }
    @PutMapping("/assign-vehicle-to-tour-package/{vehicleId}/{tourPackageId}")
    @Operation(summary = "Assign Vehicle to Tour Package")
    public Vehicle assignVehicleToTourPackage(@PathVariable("vehicleId") Long vehicleId,
                                              @PathVariable("tourPackageId") Long tourPackageId) {
        return vehicleCommandService.handle(new AssignVehicleToTourPackageCommand(vehicleId, tourPackageId));
    }
    @DeleteMapping("/remove-vehicle-to-tour-package/{vehicleId}/{tourPackageId}")
    @Operation(summary = "Remove Vehicle to Tour Package")
    public Vehicle removeVehicleToTourPackage(@PathVariable("vehicleId") Long vehicleId,
                                              @PathVariable("tourPackageId") Long tourPackageId) {
        return vehicleCommandService.handle(new RemoveVehicleToTourPackageCommand(vehicleId, tourPackageId));
    }
}
