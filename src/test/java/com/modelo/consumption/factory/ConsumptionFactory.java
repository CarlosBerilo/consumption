package com.modelo.consumption.factory;

import com.modelo.consumption.application.domain.dto.ConsumptionDTO;
import com.modelo.consumption.application.domain.dto.request.RequestConsumptionTDO;
import com.modelo.consumption.application.domain.model.Consumption;
import com.modelo.consumption.application.domain.model.Inventory;

import java.util.Optional;

public class ConsumptionFactory {

    public static RequestConsumptionTDO getRequestComposition(){
        return RequestConsumptionTDO.builder()
                .skuConsumption("123456")
                .aliasConsumption("teste")
                .value(120l)
                .inventory(Inventory.builder().credit(240L).build())
                .build();
    }

    public static Consumption getConsumption(){
        return Consumption.builder().skuConsumption("123456")
                .aliasConsumption("teste")
                .value(120L)
                .inventory(Inventory.builder().credit(240L).build()).build();
    }

    public static ConsumptionDTO getConsumptionDTO(){
        return ConsumptionDTO.builder().skuConsumption("123456")
                .aliasConsumption("teste")
                .value(120l)
                .inventory(Inventory.builder().credit(240L).build())
                .build();
    }

    public static ConsumptionDTO getConsumptionDTOCredit(){
        return ConsumptionDTO.builder().skuConsumption("123456")
                .aliasConsumption("teste")
                .value(120l)
                .inventory(Inventory.builder().credit(120L).build())
                .build();
    }

    public static ConsumptionDTO getConsumptionCredit(){
        return ConsumptionDTO.builder().skuConsumption("123456")
                .aliasConsumption("teste")
                .value(120l)
                .inventory(Inventory.builder().credit(120L).build())
                .build();
    }

    public static Optional<ConsumptionDTO> getOptionalConsumptionDTO(){
        return Optional.ofNullable(ConsumptionDTO.builder().skuConsumption("123456")
                .aliasConsumption("teste")
                .value(120l)
                .inventory(Inventory.builder().credit(240L).build())
                .build());
    }
}
