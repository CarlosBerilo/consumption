package com.modelo.consumption.adapter.in.web;

import com.modelo.consumption.application.domain.dto.request.RequestConsumptionTDO;
import com.modelo.consumption.application.domain.dto.response.ResponseConsumptionTDO;
import com.modelo.consumption.application.domain.mapper.ConsumptionMapper;
import com.modelo.consumption.application.domain.model.Consumption;
import com.modelo.consumption.application.domain.service.ConsumptionUseCase;
import com.modelo.consumption.ports.in.ConsumptionInPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/consumption")
@Tag(name = "Consumption", description = "Endpoints de Consumption")
public class ConsumptionController implements ConsumptionInPort {

    @Autowired
    private ConsumptionUseCase consumptionUseCase;

    @Autowired
    private ConsumptionMapper consumptionMapper;

    @PostMapping
    @Operation(summary = "Criar Consumo")
    @ResponseBody
    public ResponseEntity<ResponseConsumptionTDO> createConsumption(@RequestBody RequestConsumptionTDO requestConsumptionTDO) {
            Consumption createConsumption = consumptionUseCase.createConsumption(consumptionMapper.toConsumption(requestConsumptionTDO));
            return new ResponseEntity<>(consumptionMapper.toResponseConsumptionTDO(createConsumption), HttpStatus.OK);
    }

    @PutMapping("/{skuConsumption}")
    @Operation(summary = "Atualizar Consumo")
    @ResponseBody
    public ResponseEntity<ResponseConsumptionTDO> updateConsumption(@PathVariable(required = true) String skuConsumption, @RequestBody RequestConsumptionTDO requestConsumptionTDO) {
            Consumption createConsumption = consumptionUseCase.updateConsumption(skuConsumption, consumptionMapper.toConsumption(requestConsumptionTDO));
            return new ResponseEntity<>(consumptionMapper.toResponseConsumptionTDO(createConsumption), HttpStatus.OK);
    }

    @GetMapping("/{skuConsumption}")
    @Operation(summary = "Buscar Consumo")
    @ResponseBody
    public ResponseEntity<ResponseConsumptionTDO> getConsumption(@PathVariable(required = true) String skuConsumption) {
            Consumption createConsumption = consumptionUseCase.getConsumption(skuConsumption);
            return new ResponseEntity<>(consumptionMapper.toResponseConsumptionTDO(createConsumption), HttpStatus.OK);
    }

    @DeleteMapping("/{skuConsumption}")
    @Operation(summary = "Excluir Consumo")
    @ResponseBody
    public ResponseEntity<String> deleteConsumption(@PathVariable(required = true) String skuConsumption) {
            consumptionUseCase.deleteConsumption(skuConsumption);
            return new ResponseEntity<>("Consumption deletado com sucesso.", HttpStatus.OK);
    }

    @PostMapping("/consumptionCredit")
    @Operation(summary = "Consumir credito")
    @ResponseBody
    public ResponseEntity<ResponseConsumptionTDO> consumptionCredit(@RequestParam(required = true) String skuConsumption, @RequestParam(required = true) Long value) {
            Consumption createConsumption = consumptionUseCase.consumptionCredit(skuConsumption, value);
            return new ResponseEntity<>(consumptionMapper.toResponseConsumptionTDO(createConsumption), HttpStatus.OK);
    }
}
