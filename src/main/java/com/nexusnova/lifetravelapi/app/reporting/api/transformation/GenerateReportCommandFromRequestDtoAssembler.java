package com.nexusnova.lifetravelapi.app.reporting.api.transformation;

import com.nexusnova.lifetravelapi.app.reporting.domain.commands.GenerateReportCommand;
import com.nexusnova.lifetravelapi.app.reporting.resources.requests.ReportRequestDto;

public class GenerateReportCommandFromRequestDtoAssembler {
    private GenerateReportCommandFromRequestDtoAssembler() {
    }

    public static GenerateReportCommand toCommandFromDto(ReportRequestDto requestDto) {
        return new GenerateReportCommand(requestDto);
    }
}
