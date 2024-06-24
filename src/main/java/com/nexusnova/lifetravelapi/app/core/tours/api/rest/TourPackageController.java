package com.nexusnova.lifetravelapi.app.core.tours.api.rest;

import com.nexusnova.lifetravelapi.app.core.tours.api.transformation.RegisterTourPackageCommandFromRequestDtoAssembler;
import com.nexusnova.lifetravelapi.app.core.tours.domain.commands.ModifyImgPackageCommand;
import com.nexusnova.lifetravelapi.app.core.tours.domain.commands.ModifyPackageCommand;
import com.nexusnova.lifetravelapi.app.core.tours.domain.model.TourPackage;
import com.nexusnova.lifetravelapi.app.core.tours.domain.queries.GetTourPackageByIdQuery;
import com.nexusnova.lifetravelapi.app.core.tours.domain.queries.GetTourPackagesByAgencyUserIdQuery;
import com.nexusnova.lifetravelapi.app.core.tours.domain.queries.GetTourPackagesByRegionQuery;
import com.nexusnova.lifetravelapi.app.core.tours.domain.queries.GetTourPackagesByAgencyIdAndVisibilityQuery;
import com.nexusnova.lifetravelapi.app.core.tours.domain.services.TourPackageCommandService;
import com.nexusnova.lifetravelapi.app.core.tours.domain.services.TourPackageQueryService;
import com.nexusnova.lifetravelapi.app.core.tours.mapper.ToursMapper;
import com.nexusnova.lifetravelapi.app.core.tours.resources.details.TourPackageDetailDto;
import com.nexusnova.lifetravelapi.app.core.tours.resources.requests.TourPackageRequestDto;
import com.nexusnova.lifetravelapi.app.shared.constants.HeaderConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.nexusnova.lifetravelapi.app.shared.messages.ConfigurationMessages.TOUR_PACKAGE_CREATED;
import static com.nexusnova.lifetravelapi.app.shared.messages.ConfigurationMessages.TOUR_PACKAGE_MODIFIED;

@RestController
@RequestMapping("/api/v1/tour-packages")
@Tag(name="Tour Packages Controller")
@CrossOrigin
public class TourPackageController {

    private final TourPackageQueryService tourPackageQueryService;
    private final TourPackageCommandService tourPackageCommandService;
    private final ToursMapper toursMapper;

    public TourPackageController(TourPackageQueryService tourPackageQueryService,
                                 TourPackageCommandService tourPackageCommandService,
                                 ToursMapper toursMapper) {
        this.tourPackageQueryService = tourPackageQueryService;
        this.tourPackageCommandService = tourPackageCommandService;
        this.toursMapper = toursMapper;
    }
    @GetMapping
    @Operation(summary = "List existing Visible Packages")
    public List<TourPackageDetailDto> getTours() {
        List<TourPackage> tourPackages = tourPackageQueryService.handle();
        return toursMapper.tourPackageToDetailDtos(tourPackages);
    }
    @GetMapping("/all-hidden-packages-by-agency/{agencyId}")
    @Operation(summary = "List existing Hidden Packages by Agency Id")
    public List<TourPackageDetailDto> getAllHiddenPackagesByAgency(@Parameter @PathVariable("agencyId") String agencyId) {
        List<TourPackage> tourPackages = tourPackageQueryService.handle(new GetTourPackagesByAgencyIdAndVisibilityQuery(agencyId, false));
        return toursMapper.tourPackageToDetailDtos(tourPackages);
    }
    @GetMapping("/all-visible-packages-by-agency/{agencyId}")
    @Operation(summary = "List existing Visible Packages by Agency Id")
    public List<TourPackageDetailDto> getAllVisiblePackagesByAgency(@Parameter @PathVariable("agencyId") String agencyId) {
        List<TourPackage> tourPackages = tourPackageQueryService.handle(new GetTourPackagesByAgencyIdAndVisibilityQuery(agencyId, true));
        return toursMapper.tourPackageToDetailDtos(tourPackages);
    }

    @GetMapping("/region/{regionId}")
    @Operation(summary = "List existing Tour packages by Region Id")
    public List<TourPackageDetailDto> getToursByRegion(@Parameter @PathVariable("regionId") Long regionId) {
        List<TourPackage> tourPackages = tourPackageQueryService.handle(new GetTourPackagesByRegionQuery(regionId));
        return toursMapper.tourPackageToDetailDtos(tourPackages);
    }

    @GetMapping("/{packageId}")
    @Operation(summary = "Get Package by Id")
    public TourPackageDetailDto getToursById(@Parameter @PathVariable("packageId") Long packageId) {
        TourPackage tourPackage = tourPackageQueryService.handle(new GetTourPackageByIdQuery(packageId));
        return toursMapper.tourPackageToDetailDto(tourPackage);
    }
    @GetMapping("/agency/{agencyId}")
    @Operation(summary = "List existing Tour packages by Agency Id")
    public List<TourPackageDetailDto> getToursByAgency(@Parameter @PathVariable("agencyId") String agencyId) {
        List<TourPackage> tourPackages = tourPackageQueryService.handle(new GetTourPackagesByAgencyUserIdQuery(agencyId));
        return toursMapper.tourPackageToDetailDtos(tourPackages);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a Package", description = "Create a package associated to an agency.")
    public TourPackageDetailDto save(@RequestBody @Valid TourPackageRequestDto tourPackageRequestDto,
                               HttpServletResponse response) {
        TourPackage tourPackage = tourPackageCommandService.handle(RegisterTourPackageCommandFromRequestDtoAssembler.toCommandFromDto(tourPackageRequestDto));
        response.setHeader(HeaderConstants.MESSAGES, TOUR_PACKAGE_CREATED);
        return toursMapper.tourPackageToDetailDto(tourPackage);
    }

    @PutMapping("/{packageId}")
    @Operation(summary = "Modify Tour Package")
    public TourPackageDetailDto updatePackage(@Parameter @PathVariable("packageId") Long packageId,
                                              @RequestBody @Valid TourPackageRequestDto tourPackageRequestDto,
                                              HttpServletResponse response) {
        TourPackage tourPackage =
                tourPackageCommandService.handle(new ModifyPackageCommand(packageId, tourPackageRequestDto));
        response.setHeader(HeaderConstants.MESSAGES, TOUR_PACKAGE_MODIFIED);
        return toursMapper.tourPackageToDetailDto(tourPackage);
    }

    @PutMapping("/img/{packageId}")
    @Operation(summary = "Modify associated image of a Tour Package")
    public TourPackageDetailDto updateImage(@Parameter @PathVariable("packageId") Long packageId,
                                            @RequestBody Map<String, String> requestBody,
                                            HttpServletResponse response) {
        TourPackage tourPackage =
                tourPackageCommandService.handle(new ModifyImgPackageCommand(packageId, requestBody.get("imgUrl")));
        response.setHeader(HeaderConstants.MESSAGES, TOUR_PACKAGE_MODIFIED);
        return toursMapper.tourPackageToDetailDto(tourPackage);
    }
}
