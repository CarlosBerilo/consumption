package com.modelo.consumption.adapter.out;

import com.modelo.consumption.application.domain.dto.ConsumptionDTO;
import com.modelo.consumption.application.domain.mapper.ConsumptionMapper;
import com.modelo.consumption.ports.out.ConsumptionOutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Component
public class ConsumptionAdapter implements ConsumptionOutPort {
    private List<ConsumptionDTO> listConsumption = new ArrayList<>();
    @Autowired
    private ConsumptionMapper consumptionMapper;

    @Override
    public Optional<ConsumptionDTO> getConsumption(String skuConsumption) {
        return  Optional.ofNullable(listConsumption.stream().filter(c -> c.getSkuConsumption().equals(skuConsumption)).findAny().orElse(null));
    }

    @Override
    public ConsumptionDTO addConsumption(ConsumptionDTO consumptionDTO) {
        listConsumption.add(consumptionDTO);
        return listConsumption.stream().filter(c -> c.getSkuConsumption().equals(consumptionDTO.getSkuConsumption())).findAny().get();
    }

    @Override
    public ConsumptionDTO updateConsumption(String skuConsumption, ConsumptionDTO consumptionDTO) {
        int indexItem = IntStream.range(0, listConsumption.size()).filter(i -> listConsumption.get(i).getSkuConsumption().equals(skuConsumption)).findFirst().orElse(-1);
        listConsumption.set(indexItem, consumptionDTO);
        return listConsumption.stream().filter(c -> c.getSkuConsumption().equals(skuConsumption)).findAny().get();
    }

    @Override
    public void deleteConsumption(String skuConsumption) {
        int indexItem = IntStream.range(0, listConsumption.size()).filter(i -> listConsumption.get(i).getSkuConsumption().equals(skuConsumption)).findFirst().orElse(-1);
        listConsumption.remove(indexItem);
    }
}
