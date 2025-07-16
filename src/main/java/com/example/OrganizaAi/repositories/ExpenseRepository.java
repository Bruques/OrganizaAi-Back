package com.example.OrganizaAi.repositories;

import com.example.OrganizaAi.models.ExpenseModel;
import com.example.OrganizaAi.models.IncomeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<ExpenseModel, String> {
    List<ExpenseModel> findByUserId(String userId);
}
