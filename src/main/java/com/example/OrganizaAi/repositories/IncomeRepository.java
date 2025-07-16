package com.example.OrganizaAi.repositories;

import com.example.OrganizaAi.models.IncomeModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IncomeRepository extends MongoRepository<IncomeModel, String> {
    List<IncomeModel> findByUserId(String userId);
}
