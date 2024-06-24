package com.nexusnova.lifetravelapi.app.iam.profile.api.rest;

import com.nexusnova.lifetravelapi.app.iam.profile.api.transformation.RegisterTouristCommandFromDtoAssembler;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.model.Tourist;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.queries.GetTouristByUserId;
import com.nexusnova.lifetravelapi.app.iam.profile.mapper.ProfileMapper;
import com.nexusnova.lifetravelapi.app.iam.profile.resources.requests.TouristRequestDto;
import com.nexusnova.lifetravelapi.app.iam.profile.resources.summaries.TouristSummaryDto;
import com.nexusnova.lifetravelapi.app.iam.profile.service.TouristCommandService;
import com.nexusnova.lifetravelapi.app.iam.profile.service.TouristQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tourists")
@Tag(name="Tourist Controller")
@CrossOrigin
public class TouristController {

    private final TouristQueryService touristQueryService;
    private final TouristCommandService touristCommandService;
    private final ProfileMapper profileMapper;

    @Autowired
    public TouristController(TouristQueryService touristQueryService,
                             TouristCommandService touristCommandService,
                             ProfileMapper profileMapper) {
        this.touristQueryService = touristQueryService;
        this.touristCommandService = touristCommandService;
        this.profileMapper = profileMapper;
    }

    @GetMapping("/profile/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener Turista", description = "Permite obtener el perfil de un turista.")
    public TouristSummaryDto getUser(@Parameter @PathVariable("id") String id) {
        Tourist tourist = touristQueryService.handle(new GetTouristByUserId(id));
        return profileMapper.touristToSummaryDto(tourist);
    }

    @PostMapping("/register/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Registrar Turista", description = "Permite registrat el perfil de un turista.")
    public TouristSummaryDto registerAgency(@Parameter @PathVariable("id") String id,
                                           @RequestBody @Valid TouristRequestDto requestDto,
                                           HttpServletResponse response) {
        Tourist tourist = touristCommandService.handle(RegisterTouristCommandFromDtoAssembler.toCommandFromDto(id, requestDto));
        return profileMapper.touristToSummaryDto(tourist);
    }
}
