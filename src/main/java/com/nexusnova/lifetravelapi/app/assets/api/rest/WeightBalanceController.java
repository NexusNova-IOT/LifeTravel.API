package com.nexusnova.lifetravelapi.app.assets.api.rest;

import com.nexusnova.lifetravelapi.app.assets.api.transformation.UpdateWeightBalanceCommandFromRequestDtoAssembler;
import com.nexusnova.lifetravelapi.app.assets.domain.model.WeightBalance;
import com.nexusnova.lifetravelapi.app.assets.domain.queries.GetWeightBalanceByIdQuery;
import com.nexusnova.lifetravelapi.app.assets.domain.services.WeightBalanceCommandService;
import com.nexusnova.lifetravelapi.app.assets.mapper.IOTMapper;
import com.nexusnova.lifetravelapi.app.assets.resources.requests.WeightBalanceRequestDto;
import com.nexusnova.lifetravelapi.app.assets.resources.summaries.WeightBalanceSummaryDto;
import com.nexusnova.lifetravelapi.app.shared.constants.HeaderConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.nexusnova.lifetravelapi.app.shared.messages.ConfigurationMessages.WEIGHT_BALANCE_UPDATED;

@RestController
@RequestMapping("/api/v1/weight-balances")
@Tag(name="Weight Sensors Controller")
@CrossOrigin
public class WeightBalanceController {

    private final WeightBalanceCommandService weightBalanceCommandService;
    private final IOTMapper iotMapper;

    @Autowired
    public WeightBalanceController(WeightBalanceCommandService weightBalanceCommandService,
                                   IOTMapper iotMapper) {
        this.weightBalanceCommandService = weightBalanceCommandService;
        this.iotMapper = iotMapper;
    }

    @GetMapping("/{balanceId}")
    @Operation(summary = "Obtener Peso", description = "Permite ver peso.")
    public WeightBalanceSummaryDto getTemperature(@Parameter @PathVariable("balanceId") Long balanceId) {
        WeightBalance balance = weightBalanceCommandService.handle(new GetWeightBalanceByIdQuery(balanceId));
        return iotMapper.balanceToSummaryDto(balance);
    }

    @PutMapping("/update-weight/{balanceId}")
    @Operation(summary = "Actualizar Peso", description = "Permite actualizar el peso de la balanza.")
    public WeightBalanceSummaryDto updateWeight(@Parameter @PathVariable("balanceId") Long balanceId,
                                                @RequestBody @Valid WeightBalanceRequestDto requestDto,
                                                HttpServletResponse response) {
        WeightBalance balance =
                weightBalanceCommandService.handle(UpdateWeightBalanceCommandFromRequestDtoAssembler.toCommandFromDto(balanceId, requestDto));
        response.setHeader(HeaderConstants.MESSAGES, WEIGHT_BALANCE_UPDATED);
        return iotMapper.balanceToSummaryDto(balance);
    }
}
