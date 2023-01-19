package com.kp.poc.service;

import com.kp.poc.dao.EmployeeSurveyRepository;
import com.kp.poc.entity.EmployeeSurvey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSurveyServiceImpl implements EmployeeSurveyService {

    private final EmployeeSurveyRepository employeeSurveyRepository;

    public EmployeeSurveyServiceImpl(EmployeeSurveyRepository employeeSurveyRepository) {
        this.employeeSurveyRepository = employeeSurveyRepository;
    }


    @Override
    public List<EmployeeSurvey> getAllSubmittedSurvey() {
        return employeeSurveyRepository.findAllByOrderByCreatedOnDesc();
    }

    @Override
    public EmployeeSurvey submitEmployeeSurvey(EmployeeSurvey employeeSurvey) {
        return employeeSurveyRepository.save(employeeSurvey);
    }

    @Override
    public EmployeeSurvey checkAlreadySubmittedSurvey(String emailAddress) {
        return employeeSurveyRepository.findByEmailAddress(emailAddress);
    }
}
