package com.modelo.consumption.application.domain.service;

import com.modelo.consumption.application.domain.model.Consumption;

public interface ConsumptionUseCase {
    Consumption createConsumption(Consumption consumption);
    Consumption updateConsumption(String skuConsumption,Consumption consumption);
    Consumption getConsumption(String skuConsumption);
    void deleteConsumption(String skuConsumption);
    Consumption consumptionCredit(String skuConsumption, Long value);
}
