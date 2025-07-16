package com.example.OrganizaAi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseResponseDTO {
    private String id;
    private String description;
    private BigDecimal value;
    private LocalDate data;
    private String category;
}