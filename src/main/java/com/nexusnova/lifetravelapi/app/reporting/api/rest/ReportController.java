package com.nexusnova.lifetravelapi.app.reporting.api.rest;

import com.nexusnova.lifetravelapi.app.reporting.api.transformation.GenerateReportCommandFromRequestDtoAssembler;
import com.nexusnova.lifetravelapi.app.reporting.api.transformation.ReportSummaryAssembler;
import com.nexusnova.lifetravelapi.app.reporting.domain.commands.RemoveReportCommand;
import com.nexusnova.lifetravelapi.app.reporting.domain.queries.GetReportByAgencyQuery;
import com.nexusnova.lifetravelapi.app.reporting.domain.model.Report;
import com.nexusnova.lifetravelapi.app.reporting.domain.services.ReportCommandService;
import com.nexusnova.lifetravelapi.app.reporting.domain.services.ReportQueryService;
import com.nexusnova.lifetravelapi.app.reporting.resources.requests.ReportRequestDto;
import com.nexusnova.lifetravelapi.app.reporting.resources.summaries.ReportSummaryDto;
import com.nexusnova.lifetravelapi.app.shared.constants.HeaderConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nexusnova.lifetravelapi.app.shared.messages.ConfigurationMessages.REPORT_GENERATED;

@RestController
@RequestMapping("/api/v1/reports")
@Tag(name="Report Controller")
@CrossOrigin
public class ReportController {
    private final ReportCommandService reportCommandService;
    private final ReportQueryService reportQueryService;
    private final ReportSummaryAssembler reportSummaryAssembler;

    @Autowired
    public ReportController(ReportCommandService reportCommandService, ReportQueryService reportQueryService, ReportSummaryAssembler reportSummaryAssembler) {
        this.reportCommandService = reportCommandService;
        this.reportQueryService = reportQueryService;
        this.reportSummaryAssembler = reportSummaryAssembler;
    }

    @GetMapping("/")
    @Operation(summary = "Get all reports", description = "Get all reports")
    @ResponseStatus(HttpStatus.OK)
    public List<ReportSummaryDto> getReports() {
        List<Report> reports = reportQueryService.handle();
        return reportSummaryAssembler.toSummaryFromData(reports);
    }

    @GetMapping("agency/{agencyId}")
    @Operation(summary = "Get report by agency id", description = "Get report by agency id")
    @ResponseStatus(HttpStatus.OK)
    public List<ReportSummaryDto> getReportsByAgencyId(@Parameter @PathVariable("agencyId") Long agencyId) {
        List<Report> reports = reportQueryService.handle(new GetReportByAgencyQuery(agencyId));
        return reportSummaryAssembler.toSummaryFromData(reports);
    }

    @PostMapping("/")
    @Operation(summary = "Create report", description = "Create a new report")
    @ResponseStatus(HttpStatus.CREATED)
    public ReportSummaryDto createReport(@RequestBody @Valid ReportRequestDto reportRequestDto, HttpServletResponse response) {
        Report report = reportCommandService.handle(GenerateReportCommandFromRequestDtoAssembler.toCommandFromDto(reportRequestDto));
        response.setHeader(HeaderConstants.MESSAGES, REPORT_GENERATED);
        return reportSummaryAssembler.toSummaryFromData(report);
    }

    @DeleteMapping("/{reportId}")
    @Operation(summary = "Delete report", description = "Delete a report")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ReportSummaryDto deleteReport(@Parameter @PathVariable("reportId") Long reportId) {
        Report report = reportCommandService.handle(new RemoveReportCommand(reportId));
        return reportSummaryAssembler.toSummaryFromData(report);
    }

}
