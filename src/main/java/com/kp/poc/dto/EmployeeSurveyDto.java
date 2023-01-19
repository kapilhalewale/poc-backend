package com.kp.poc.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeeSurveyDto {
    private String fullName;
    private String emailAddress;
    private String resourceAvailability;
    private String responsibilityClarity;
    private String workCulture;
    private String teaQuality;
    private String overallSatisfaction;
    private String briefComment;
}