package com.example.OrganizaAi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DashboardDTO {
    private Double totalIncome;
    private Double totalExpenses;
    private Double balance;
    private Double estimatedTax;
    private Map<String, Double> incomingByCategory;
    private Map<String, Double> expensesByCategory;
}
