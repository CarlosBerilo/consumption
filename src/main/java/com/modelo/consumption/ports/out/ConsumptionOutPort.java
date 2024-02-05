package com.modelo.consumption.ports.out;

import com.modelo.consumption.application.domain.dto.ConsumptionDTO;
import com.modelo.consumption.application.domain.model.Consumption;

import java.util.Optional;

public interface ConsumptionOutPort{
    Optional<ConsumptionDTO> getConsumption(String skuConsumption);

    ConsumptionDTO addConsumption(ConsumptionDTO consumptionDTO);

    ConsumptionDTO updateConsumption(String skuConsumption, ConsumptionDTO consumptionDTO);

    void deleteConsumption(String skuConsumption);

}
