package com.kp.poc.dto;

import com.kp.poc.entity.EmployeeSurvey;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EmployeeSurveyResponse {
    private List<EmployeeSurvey> employeeSurveys;
    Map<String, Map<String, Long>> additionalData;
}
