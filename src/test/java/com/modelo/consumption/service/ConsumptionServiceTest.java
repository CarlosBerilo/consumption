package com.modelo.consumption.service;

import com.modelo.consumption.adapter.out.ConsumptionAdapter;
import com.modelo.consumption.application.domain.mapper.ConsumptionMapper;
import com.modelo.consumption.application.domain.service.ConsumptionService;
import com.modelo.consumption.factory.ConsumptionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ConsumptionServiceTest")
public class ConsumptionServiceTest {

    @Mock
    private ConsumptionMapper consumptionMapper;
    @Mock
    private ConsumptionAdapter consumptionAdapter;
    @InjectMocks
    private ConsumptionService consumptionService;

    private static final String SKU_CONSUMPTION = "123456";

    @Test
    @DisplayName("[method=createConsumption] When createConsumption success")
    void test_createConsumption_ok(){
        when(consumptionAdapter.getConsumption(Mockito.anyString())).thenReturn(Optional.empty());
        when(consumptionAdapter.addConsumption(ConsumptionFactory.getConsumptionDTO())).thenReturn(ConsumptionFactory.getConsumptionDTO());
        when(consumptionMapper.toConsumptionDTO(ConsumptionFactory.getConsumption())).thenReturn(ConsumptionFactory.getConsumptionDTO());
        when(consumptionMapper.toConsumption(ConsumptionFactory.getConsumptionDTO())).thenReturn(ConsumptionFactory.getConsumption());

        var expected = consumptionService.createConsumption(ConsumptionFactory.getConsumption());
        var actual = ConsumptionFactory.getConsumption();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    @DisplayName("[method=updateConsumption] When updateConsumption success")
    void test_updateConsumption_ok(){
        when(consumptionMapper.toConsumptionDTO(ConsumptionFactory.getConsumption())).thenReturn(ConsumptionFactory.getConsumptionDTO());
        when(consumptionMapper.toConsumption(ConsumptionFactory.getConsumptionDTO())).thenReturn(ConsumptionFactory.getConsumption());
        when(consumptionAdapter.updateConsumption(SKU_CONSUMPTION, ConsumptionFactory.getConsumptionDTO())).thenReturn(ConsumptionFactory.getConsumptionDTO());

        var expected = consumptionService.updateConsumption(SKU_CONSUMPTION, ConsumptionFactory.getConsumption());
        var actual = ConsumptionFactory.getConsumption();
        Assertions.assertEquals(expected, actual);

    }
    @Test
    @DisplayName("[method=getConsumption] When getConsumption success")
    void test_getConsumption_ok(){
        when(consumptionAdapter.getConsumption(Mockito.anyString())).thenReturn(ConsumptionFactory.getOptionalConsumptionDTO());
        when(consumptionMapper.toConsumption(ConsumptionFactory.getConsumptionDTO())).thenReturn(ConsumptionFactory.getConsumption());

        var expected = consumptionService.getConsumption(SKU_CONSUMPTION);
        var actual = ConsumptionFactory.getConsumption();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("[method=createConsumption] When consumption Credit success")
    void test_consumptionCredit_ok(){
        when(consumptionAdapter.getConsumption(Mockito.anyString())).thenReturn(ConsumptionFactory.getOptionalConsumptionDTO());
        when(consumptionMapper.toConsumptionDTO(ConsumptionFactory.getConsumptionCredit())).thenReturn(ConsumptionFactory.getConsumptionDTOCredit());
        when(consumptionAdapter.updateConsumption(SKU_CONSUMPTION, ConsumptionFactory.getConsumptionDTOCredit())).thenReturn(ConsumptionFactory.getConsumptionDTOCredit());

        var expected = consumptionService.consumptionCredit(SKU_CONSUMPTION, 120l);
        var actual = ConsumptionFactory.getConsumptionDTOCredit();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("[method=deleteConsumption] When delete Consumption success")
    void test_deleteConsumption_ok(){
        doNothing().when(consumptionAdapter).deleteConsumption(Mockito.anyString());
        consumptionService.deleteConsumption(SKU_CONSUMPTION);
        verify(consumptionAdapter).deleteConsumption(SKU_CONSUMPTION);
    }

    @Test
    @DisplayName("[method=consumptionCredit] When consumption Credit skuConsumption não encontrado")
    void test_consumptionCredit_error(){
        when(consumptionAdapter.getConsumption(Mockito.anyString())).thenReturn(Optional.empty());
        String expected = "";
        try {
            consumptionService.consumptionCredit(SKU_CONSUMPTION, 120l);
        }catch (Exception e){
            expected = e.getMessage();
        }
        String actual = "SkuConsumption não encontrado.";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("[method=consumptionCredit] When consumption Credit insuficiente.")
    void test_consumptionCredit_credit_error(){
        when(consumptionAdapter.getConsumption(Mockito.anyString())).thenReturn(ConsumptionFactory.getOptionalConsumptionDTO());
        String expected = "";
        try {
            consumptionService.consumptionCredit(SKU_CONSUMPTION, 260l);
        }catch (Exception e){
            expected = e.getMessage();
        }
        String actual = "Credito Insuficiente.";
        Assertions.assertEquals(expected, actual);
    }

}
