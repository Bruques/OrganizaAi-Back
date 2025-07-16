package com.example.OrganizaAi.controllers;

import com.example.OrganizaAi.dto.ExpenseRequestDTO;
import com.example.OrganizaAi.dto.IncomeRequestDTO;
import com.example.OrganizaAi.models.ExpenseModel;
import com.example.OrganizaAi.models.IncomeModel;
import com.example.OrganizaAi.security.TokenService;
import com.example.OrganizaAi.services.ExpenseService;
import com.example.OrganizaAi.services.IncomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final TokenService tokenService;

    public ExpenseController(ExpenseService expenseService,
                            TokenService tokenService) {
        this.expenseService = expenseService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<ExpenseModel> newExpense(@RequestBody ExpenseRequestDTO dto,
                                                   @RequestHeader("Authorization") String token) {
        String userId = tokenService.extractUserIdFromHeader(token);
        ExpenseModel expense = new ExpenseModel();
        expense.setUserId(userId);
        expense.setDescription(dto.getDescription());
        expense.setCategory(dto.getCategory());
        expense.setValue(dto.getValue());
        expense.setData(dto.getData());

        ExpenseModel newExpense = expenseService.newExpense(expense);
        return ResponseEntity.ok(newExpense);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseModel>> getExpenseList(@RequestHeader("Authorization") String token) {
        String userId = tokenService.extractUserIdFromHeader(token);
        return ResponseEntity.ok(expenseService.getExpenseList(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseModel> updateExpense(@PathVariable String id, @RequestBody ExpenseRequestDTO dto) {
        ExpenseModel newExpense = new ExpenseModel();
        newExpense.setDescription(dto.getDescription());
        newExpense.setValue(dto.getValue());
        newExpense.setCategory(dto.getCategory());
        newExpense.setData(dto.getData());
        ExpenseModel editedExpense = expenseService.updateExpense(id, newExpense);
        return ResponseEntity.ok(editedExpense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExpenseModel> deleteExpense(@PathVariable String id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
