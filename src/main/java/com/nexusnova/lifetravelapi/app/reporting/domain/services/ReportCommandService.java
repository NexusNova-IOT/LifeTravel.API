package com.nexusnova.lifetravelapi.app.reporting.domain.services;

import com.nexusnova.lifetravelapi.app.reporting.domain.commands.GenerateReportCommand;
import com.nexusnova.lifetravelapi.app.reporting.domain.commands.RemoveReportCommand;
import com.nexusnova.lifetravelapi.app.reporting.domain.model.Report;

public interface ReportCommandService {
    Report handle(GenerateReportCommand command);
    Report handle(RemoveReportCommand command);
}
