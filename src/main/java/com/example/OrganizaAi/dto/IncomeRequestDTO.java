package com.example.OrganizaAi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IncomeRequestDTO {
    private String description;
    private BigDecimal value;
    private LocalDate data;
    private String category;
}
