package com.modelo.consumption.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modelo.consumption.adapter.in.web.ConsumptionController;
import com.modelo.consumption.application.domain.mapper.ConsumptionMapper;
import com.modelo.consumption.application.domain.service.ConsumptionService;
import com.modelo.consumption.factory.ConsumptionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ConsumptionController.class)
public class ConsumptionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ConsumptionService consumptionService;
    @MockBean
    private ConsumptionMapper consumptionMapper;

    private static final String URL_CONSUMPTION = "/consumption";
    private static final String SKU_CONSUMPTION = "23456";
    private static final String VALUE_CONSUMPTION = "369";
    private static final String URL_CONSUMPTION_CREDIT = "consumptionCredit";

    @Test
    @DisplayName("[createConsumption] Criação de consumption")
    void test_create_consumption() throws Exception {
        ResultActions response = mockMvc.perform(
                  MockMvcRequestBuilders.post(URL_CONSUMPTION)
                          .contentType(MediaType.APPLICATION_JSON)
                          .content(objectMapper.writeValueAsString(ConsumptionFactory.getRequestComposition())));
        response.andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        );
    }

    @Test
    @DisplayName("[updteConsumption] Atualização de consumption")
    void test_updte_consumption() throws Exception {
        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.put(URL_CONSUMPTION + "/" + SKU_CONSUMPTION)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ConsumptionFactory.getRequestComposition())));
        response.andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        );
    }

    @Test
    @DisplayName("[getConsumption] Busca de consumption")
    void test_get_consumption() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(URL_CONSUMPTION+ "/" + SKU_CONSUMPTION));
        response.andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        );
    }

    @Test
    @DisplayName("[deleteConsumption] Delete de  consumption")
    void test_delete_consumption() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete(URL_CONSUMPTION+ "/" + SKU_CONSUMPTION));
        response.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @DisplayName("[consumptionCredit] Consumption of credit")
    void test_consumption_credit() throws Exception {
        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.post(URL_CONSUMPTION +"/"+ URL_CONSUMPTION_CREDIT)
                        .param("skuConsumption", SKU_CONSUMPTION)
                        .param("value", VALUE_CONSUMPTION));
        response.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}
