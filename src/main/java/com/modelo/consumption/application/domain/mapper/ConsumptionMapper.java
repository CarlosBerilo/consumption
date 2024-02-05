package com.modelo.consumption.application.domain.mapper;

import com.modelo.consumption.application.domain.dto.ConsumptionDTO;
import com.modelo.consumption.application.domain.dto.request.RequestConsumptionTDO;
import com.modelo.consumption.application.domain.dto.response.ResponseConsumptionTDO;
import com.modelo.consumption.application.domain.model.Consumption;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsumptionMapper {
    ResponseConsumptionTDO toResponseConsumptionTDO(Consumption consumption);
    Consumption toConsumption(RequestConsumptionTDO requestConsumptionTDO);
    ConsumptionDTO toConsumptionDTO(Consumption consumption);
    Consumption toConsumption(ConsumptionDTO toConsumptionDTO);

}
