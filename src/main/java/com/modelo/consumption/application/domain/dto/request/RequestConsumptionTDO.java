package com.modelo.consumption.application.domain.dto.request;

import com.modelo.consumption.application.domain.model.Consumption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
public class RequestConsumptionTDO extends Consumption {
}
