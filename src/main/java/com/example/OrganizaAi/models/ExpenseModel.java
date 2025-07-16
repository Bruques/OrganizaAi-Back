package com.example.OrganizaAi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "expense")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpenseModel {
    @Id
    private String id;

    private String userId;
    private String description;
    private BigDecimal value;
    private LocalDate data;
    private String category;
}