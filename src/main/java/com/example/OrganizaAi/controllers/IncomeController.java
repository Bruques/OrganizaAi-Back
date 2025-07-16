package com.example.OrganizaAi.controllers;

import com.example.OrganizaAi.dto.IncomeRequestDTO;
import com.example.OrganizaAi.models.IncomeModel;
import com.example.OrganizaAi.security.TokenService;
import com.example.OrganizaAi.services.IncomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/income")
public class IncomeController {

    private final IncomeService incomeService;
    private final TokenService tokenService;

    public IncomeController(IncomeService incomeService,
                            TokenService tokenService) {
        this.incomeService = incomeService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<IncomeModel> newIncome(@RequestBody IncomeRequestDTO dto,
                                                 @RequestHeader("Authorization") String token) {
        String userId = tokenService.extractUserIdFromHeader(token);
        IncomeModel income = new IncomeModel();
        income.setUserId(userId);
        income.setDescription(dto.getDescription());
        income.setCategory(dto.getCategory());
        income.setValue(dto.getValue());
        income.setData(dto.getData());

        IncomeModel newIncome = incomeService.newIncome(income);
        return ResponseEntity.ok(newIncome);
    }

    @GetMapping
    public ResponseEntity<List<IncomeModel>> getIncomeList(@RequestHeader("Authorization") String token) {
        String userId = tokenService.extractUserIdFromHeader(token);
        return ResponseEntity.ok(incomeService.getIncomeList(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeModel> updateIncome(@PathVariable String id, @RequestBody IncomeRequestDTO dto) {
        IncomeModel newIncome = new IncomeModel();
        newIncome.setDescription(dto.getDescription());
        newIncome.setValue(dto.getValue());
        newIncome.setCategory(dto.getCategory());
        newIncome.setData(dto.getData());
        IncomeModel editedIncome = incomeService.updateIncome(id, newIncome);
        return ResponseEntity.ok(editedIncome);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IncomeModel> deleteIncome(@PathVariable String id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }
}
