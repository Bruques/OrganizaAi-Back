package com.example.OrganizaAi.services;

import com.example.OrganizaAi.models.IncomeModel;
import com.example.OrganizaAi.repositories.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {
    private IncomeRepository repository;

    public IncomeService(IncomeRepository repository) {
        this.repository = repository;
    }

    public IncomeModel newIncome(IncomeModel income) {
        return repository.save(income);
    }

    public List<IncomeModel> getIncomeList(String userId) {
        return repository.findByUserId(userId);
    }

    public IncomeModel updateIncome(String id, IncomeModel newIncome) {
        IncomeModel income = repository.findById(id).orElseThrow(() -> new RuntimeException("Income not found"));
        income.setDescription(newIncome.getDescription());
        income.setValue(newIncome.getValue());
        income.setData(newIncome.getData());
        income.setCategory(newIncome.getCategory());
        return repository.save(income);
    }

    public IncomeModel deleteIncome(String id) {
        Optional<IncomeModel> incomeOptional = repository.findById(id);
        if (incomeOptional.isPresent()) {
            IncomeModel incomeToDelete = incomeOptional.get();
            repository.delete(incomeToDelete);
            return incomeToDelete;
        } else {
            throw new RuntimeException("Income with ID" + id + "not found");
        }
    }
}
