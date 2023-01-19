package com.kp.poc.service;

import com.kp.poc.entity.EmployeeSurvey;
import java.util.List;

public interface EmployeeSurveyService {
    List<EmployeeSurvey> getAllSubmittedSurvey();
    EmployeeSurvey submitEmployeeSurvey(EmployeeSurvey employeeSurvey);
    EmployeeSurvey checkAlreadySubmittedSurvey(String emailAddress);
}