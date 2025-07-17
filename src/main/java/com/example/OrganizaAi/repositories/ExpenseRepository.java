package com.example.OrganizaAi.repositories;

import com.example.OrganizaAi.models.ExpenseModel;
import com.example.OrganizaAi.models.IncomeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<ExpenseModel, String> {
    List<ExpenseModel> findByUserId(String userId);

    @Query("{ 'userId': ?0, $expr: { $and: [ { $eq: [{ $month: '$data' }, ?1] }, { $eq: [{ $year: '$data' }, ?2] } ] } }")
    List<ExpenseModel> findAllByUserIdAndMonth(String userId, int mes, int ano);
}
