package com.modelo.consumption.application.domain.service;

import com.modelo.consumption.adapter.out.ConsumptionAdapter;
import com.modelo.consumption.application.domain.dto.ConsumptionDTO;
import com.modelo.consumption.application.domain.exception.ConsumptionException;
import com.modelo.consumption.application.domain.mapper.ConsumptionMapper;
import com.modelo.consumption.application.domain.model.Consumption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

/*
Requisitos

 1 - Toda vez que você chamar o endpoint de consumo passando um valor ele deve consumir o saldo baseado no credit

 2 - Caso não tenha saldo uma exceção deverá ser lançada

 3 - Caso um produto já existente em memória tente ser criado com o mesmo sku uma exceção deverá ser lançada

 4 - Dois produtos são considerados iguais se os seus skus forem iguais

 5 - Ao atualizar um produto, o antigo deve ser sobrescrito com o que esta sendo enviado na requisição

 6 - A requisição deve receber o sku e atualizar com o produto que tbm esta vindo na requisição
 */
@Component
public class ConsumptionService implements ConsumptionUseCase {

    @Autowired
    private ConsumptionMapper consumptionMapper;
    @Autowired
    private ConsumptionAdapter consumptionAdapter;

    @Override
    public Consumption createConsumption(Consumption consumption) {
        //TODO: 3 - Caso um produto já existente em memória tente ser criado com o mesmo sku uma exceção deverá ser lançada
        //TODO:  4 - Dois produtos são considerados iguais se os seus skus forem iguais
        Optional<ConsumptionDTO> getConsumptionDTO = consumptionAdapter.getConsumption(consumption.getSkuConsumption());
        if(getConsumptionDTO.isPresent()){
            throw new ConsumptionException("Produto já existente.");
        }else{
            return consumptionMapper.toConsumption(consumptionAdapter.addConsumption(consumptionMapper.toConsumptionDTO(consumption)));
        }

    }

    @Override
    public Consumption updateConsumption(String skuConsumption, Consumption consumption) {
        //TODO: 5 - Ao atualizar um produto, o antigo deve ser sobrescrito com o que esta sendo enviado na requisição
        try {
            return consumptionMapper.toConsumption(consumptionAdapter.updateConsumption(skuConsumption, consumptionMapper.toConsumptionDTO(consumption)));
        }catch (IndexOutOfBoundsException ex){
            throw new ConsumptionException("SkuConsumption não encontrado.");
        }catch (NoSuchElementException ex){
            throw new ConsumptionException("SkuConsumption são diferentes.");
        }
    }

    @Override
    public Consumption getConsumption(String skuConsumption) {
        try {
            Optional<ConsumptionDTO> getConsumptionDTOs = consumptionAdapter.getConsumption(skuConsumption);
            return consumptionMapper.toConsumption(getConsumptionDTOs.get());
        }catch (NoSuchElementException ex){
            throw new ConsumptionException("SkuConsumption não encontrado.");
        }
    }

    @Override
    public void deleteConsumption(String skuConsumption) {
        try {
            consumptionAdapter.deleteConsumption(skuConsumption);
        }catch (IndexOutOfBoundsException ex){
            throw new ConsumptionException("SkuConsumption não encontrado.");
        }
    }

    @Override
    public Consumption consumptionCredit(String skuConsumption, Long value) {
        Optional<ConsumptionDTO> consumptionExist = consumptionAdapter.getConsumption(skuConsumption);
        if (consumptionExist.isPresent()) {
            Consumption consumption = consumptionExist.get();
            Long credit = consumption.getInventory().getCredit();
            if (credit >= value) {
                //TODO: 1 - Toda vez que você chamar o endpoint de consumo passando um valor ele deve consumir o saldo baseado no credit
                Long creditUpdate = credit - value;
                consumption.getInventory().setCredit(creditUpdate);
                return consumptionAdapter.updateConsumption(consumption.getSkuConsumption(), consumptionMapper.toConsumptionDTO(consumption));
            } else {
                //TODO: 2 - Caso não tenha saldo uma exceção deverá ser lançada
                throw new ConsumptionException("Credito Insuficiente.");
            }
        } else {
            throw new ConsumptionException("SkuConsumption não encontrado.");
        }
    }
}
