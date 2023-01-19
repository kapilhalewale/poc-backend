package com.kp.poc.dao;

import com.kp.poc.entity.EmployeeSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeSurveyRepository extends JpaRepository<EmployeeSurvey, Long> {
    EmployeeSurvey findByEmailAddress(String emailAddress);
    List<EmployeeSurvey> findAllByOrderByCreatedOnDesc();

}