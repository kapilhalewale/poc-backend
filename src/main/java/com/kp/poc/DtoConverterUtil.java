package com.kp.poc;

import com.kp.poc.dto.EmployeeSurveyDto;
import com.kp.poc.entity.EmployeeSurvey;

import java.util.Date;

public class DtoConverterUtil {
    public static EmployeeSurvey convertEmployeeSurveyDto(EmployeeSurveyDto employeeSurveyDto){
        EmployeeSurvey employeeSurvey = new EmployeeSurvey();
        employeeSurvey.setFullName(employeeSurveyDto.getFullName());
        employeeSurvey.setEmailAddress(employeeSurveyDto.getEmailAddress());
        employeeSurvey.setResourceAvailability(employeeSurveyDto.getResourceAvailability());
        employeeSurvey.setWorkCulture(employeeSurveyDto.getWorkCulture());
        employeeSurvey.setOverallSatisfaction(employeeSurveyDto.getOverallSatisfaction());
        employeeSurvey.setResponsibilityClarity(employeeSurveyDto.getResponsibilityClarity());
        employeeSurvey.setTeaQuality(employeeSurveyDto.getTeaQuality());
        employeeSurvey.setBriefComment(employeeSurveyDto.getBriefComment());
        employeeSurvey.setCreatedOn(new Date());
        return employeeSurvey;
    }
}