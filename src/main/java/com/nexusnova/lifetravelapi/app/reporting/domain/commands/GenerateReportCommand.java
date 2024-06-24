package com.nexusnova.lifetravelapi.app.reporting.domain.commands;

import com.nexusnova.lifetravelapi.app.reporting.resources.requests.ReportRequestDto;

public record GenerateReportCommand(ReportRequestDto requestDto) {
}
