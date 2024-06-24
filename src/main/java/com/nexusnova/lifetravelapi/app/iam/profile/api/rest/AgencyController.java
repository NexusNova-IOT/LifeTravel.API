package com.nexusnova.lifetravelapi.app.iam.profile.api.rest;

import com.nexusnova.lifetravelapi.app.iam.profile.api.transformation.RegisterAgencyCommandFromDtoAssembler;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.model.Agency;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.queries.GetAgencyByUserId;
import com.nexusnova.lifetravelapi.app.iam.profile.mapper.ProfileMapper;
import com.nexusnova.lifetravelapi.app.iam.profile.resources.requests.AgencyRequestDto;
import com.nexusnova.lifetravelapi.app.iam.profile.resources.summaries.AgencySummaryDto;
import com.nexusnova.lifetravelapi.app.iam.profile.service.AgencyCommandService;
import com.nexusnova.lifetravelapi.app.iam.profile.service.AgencyQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/agencies")
@Tag(name="Agency Controller")
@CrossOrigin
public class AgencyController {

    private final AgencyQueryService agencyQueryService;
    private final AgencyCommandService agencyCommandService;
    private final ProfileMapper profileMapper;

    @Autowired
    public AgencyController(AgencyQueryService agencyQueryService,
                            AgencyCommandService agencyCommandService,
                            ProfileMapper profileMapper) {
        this.agencyQueryService = agencyQueryService;
        this.agencyCommandService = agencyCommandService;
        this.profileMapper = profileMapper;
    }

    @GetMapping("/profile/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener Agencia", description = "Permite obtener el perfil de una agencia.")
    public AgencySummaryDto getUser(@Parameter @PathVariable("id") String id) {
        Agency agency = agencyQueryService.handle(new GetAgencyByUserId(id));
        return profileMapper.agencyToSummaryDto(agency);
    }

    @PostMapping("/register/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Registrar Agencia", description = "Permite registrat el perfil de una agencia.")
    public AgencySummaryDto registerAgency(@Parameter @PathVariable("id") String id,
                                           @RequestBody @Valid AgencyRequestDto requestDto,
                                           HttpServletResponse response) {
        Agency agency = agencyCommandService.handle(RegisterAgencyCommandFromDtoAssembler.toCommandFromDto(id, requestDto));
        return profileMapper.agencyToSummaryDto(agency);
    }
}
