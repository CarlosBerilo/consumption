package com.modelo.consumption.ports.in;

import com.modelo.consumption.application.domain.dto.request.RequestConsumptionTDO;
import com.modelo.consumption.application.domain.dto.response.ResponseConsumptionTDO;
import org.springframework.http.ResponseEntity;

public interface ConsumptionInPort {
    ResponseEntity<ResponseConsumptionTDO> createConsumption(RequestConsumptionTDO requestConsumptionTDO);
    ResponseEntity<ResponseConsumptionTDO> updateConsumption(String skuConsumption, RequestConsumptionTDO requestConsumptionTDO);
    ResponseEntity<ResponseConsumptionTDO> getConsumption(String skuConsumption);
    ResponseEntity<String> deleteConsumption(String skuConsumption);
    ResponseEntity<ResponseConsumptionTDO> consumptionCredit(String skuConsumption, Long value);
}
