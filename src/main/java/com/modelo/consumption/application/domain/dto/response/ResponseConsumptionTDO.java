package com.modelo.consumption.application.domain.dto.response;

import com.modelo.consumption.application.domain.model.Consumption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
public class ResponseConsumptionTDO extends Consumption {
}
