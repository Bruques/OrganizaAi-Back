package com.example.OrganizaAi.services;

import com.example.OrganizaAi.models.ExpenseModel;
import com.example.OrganizaAi.models.IncomeModel;
import com.example.OrganizaAi.repositories.ExpenseRepository;
import com.example.OrganizaAi.repositories.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public ExpenseModel newExpense(ExpenseModel expense) {
        return repository.save(expense);
    }

    public List<ExpenseModel> getExpenseList(String userId) {
        return repository.findByUserId(userId);
    }

    public ExpenseModel updateExpense(String id, ExpenseModel newExpense) {
        ExpenseModel expense = repository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        expense.setDescription(newExpense.getDescription());
        expense.setValue(newExpense.getValue());
        expense.setData(newExpense.getData());
        expense.setCategory(newExpense.getCategory());
        return repository.save(expense);
    }

    public ExpenseModel deleteExpense(String id) {
        Optional<ExpenseModel> expenseOptional = repository.findById(id);
        if (expenseOptional.isPresent()) {
            ExpenseModel expenseToDelete = expenseOptional.get();
            repository.delete(expenseToDelete);
            return expenseToDelete;
        } else {
            throw new RuntimeException("expense with ID" + id + "not found");
        }
    }
}
