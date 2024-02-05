package com.modelo.consumption.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consumption {
    private String skuConsumption;
    private String aliasConsumption;
    private Long value;
    private Inventory inventory;
}
