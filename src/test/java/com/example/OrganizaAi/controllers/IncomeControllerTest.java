package com.example.OrganizaAi.controllers;

import com.example.OrganizaAi.dto.IncomeRequestDTO;
import com.example.OrganizaAi.models.IncomeModel;
import com.example.OrganizaAi.security.TokenService;
import com.example.OrganizaAi.services.IncomeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IncomeControllerTest {
    @InjectMocks
    private IncomeController incomeController;

    @Mock
    private IncomeService incomeService;

    @Mock
    private TokenService tokenService;

    @Test
    public void shouldCreateIncomeWhenValidDTOIsGiven() {
        String token = "Bearer fake.jwt.token";
        String userId = "123";

        IncomeRequestDTO dto = new IncomeRequestDTO();
        dto.setDescription("Serviço de design");
        dto.setCategory("Serviços");
        dto.setValue(new BigDecimal("1500.00"));
        dto.setData(LocalDate.of(2025, 7, 14));

        IncomeModel expectedIncome = new IncomeModel();
        expectedIncome.setId("abc123");
        expectedIncome.setUserId(userId);
        expectedIncome.setDescription(dto.getDescription());
        expectedIncome.setCategory(dto.getCategory());
        expectedIncome.setValue(dto.getValue());
        expectedIncome.setData(dto.getData());

        when(tokenService.extractUserIdFromHeader(token)).thenReturn(userId);
        when(incomeService.newIncome(any(IncomeModel.class))).thenReturn(expectedIncome);

        ResponseEntity<IncomeModel> response = incomeController.newIncome(dto, token);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedIncome, response.getBody());
    }
}
