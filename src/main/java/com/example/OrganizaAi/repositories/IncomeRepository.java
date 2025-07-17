package com.example.OrganizaAi.repositories;

import com.example.OrganizaAi.models.IncomeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IncomeRepository extends MongoRepository<IncomeModel, String> {
    List<IncomeModel> findByUserId(String userId);

    @Query("{ 'userId': ?0, $expr: { $and: [ { $eq: [{ $month: '$data' }, ?1] }, { $eq: [{ $year: '$data' }, ?2] } ] } }")
    List<IncomeModel> findAllByUserIdAndMonth(String userId, int mes, int ano);
}
