package com.example.OrganizaAi.services;

import com.example.OrganizaAi.dto.DashboardDTO;
import com.example.OrganizaAi.models.ExpenseModel;
import com.example.OrganizaAi.models.IncomeModel;
import com.example.OrganizaAi.repositories.ExpenseRepository;
import com.example.OrganizaAi.repositories.IncomeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public DashboardService(IncomeRepository incomeRepository,
                            ExpenseRepository expenseRepository) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
    }

    public DashboardDTO getDashboardData(String userId, int month, int year) {
        List<IncomeModel> incomes = incomeRepository.findAllByUserIdAndMonth(userId, month, year);
        List<ExpenseModel> expenses = expenseRepository.findAllByUserIdAndMonth(userId, month, year);

        BigDecimal totalIncome = incomes
                .stream()
                .map(IncomeModel::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpense = expenses
                .stream()
                .map(ExpenseModel::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalIncome.subtract(totalExpense);
        BigDecimal estimatedTex = totalIncome.multiply(new BigDecimal("0.06"));

        Map<String, BigDecimal> incomeByCategory = incomes
                .stream()
                .collect(Collectors.groupingBy(
                        IncomeModel::getCategory,
                        Collectors.mapping(IncomeModel::getValue,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ));

        Map<String, BigDecimal> expenseByCategory = expenses.stream()
                .collect(Collectors.groupingBy(
                        ExpenseModel::getCategory,
                        Collectors.mapping(ExpenseModel::getValue,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ));

        Map<String, Double> incomeByCategoryDTO = incomeByCategory.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().doubleValue()));

        Map<String, Double> expenseByCategoryDTO = expenseByCategory.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().doubleValue()));

        DashboardDTO dto = new DashboardDTO();
        dto.setTotalIncome(totalIncome.doubleValue());
        dto.setTotalExpenses(totalExpense.doubleValue());
        dto.setBalance(balance.doubleValue());
        dto.setEstimatedTax(estimatedTex.doubleValue());
        dto.setIncomingByCategory(incomeByCategoryDTO);
        dto.setExpensesByCategory(expenseByCategoryDTO);

        return dto;
    }
}
