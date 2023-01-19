package com.kp.poc.controller;

import com.kp.poc.DtoConverterUtil;
import com.kp.poc.dto.EmployeeSurveyDto;
import com.kp.poc.dto.EmployeeSurveyResponse;
import com.kp.poc.entity.EmployeeSurvey;
import com.kp.poc.service.EmployeeSurveyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class EmployeeSurveyController {

    private final EmployeeSurveyService employeeSurveyService;

    public EmployeeSurveyController(EmployeeSurveyService employeeSurveyService) {
        this.employeeSurveyService = employeeSurveyService;
    }

    @PostMapping("/employee/survey")
    public ResponseEntity submitEmployeeSurvey(@RequestBody EmployeeSurveyDto employeeSurveyDto){
        log.info("submitEmployeeSurvey" + employeeSurveyDto.toString());
        employeeSurveyService.submitEmployeeSurvey(DtoConverterUtil.convertEmployeeSurveyDto(employeeSurveyDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/employee/survey")
    public ResponseEntity<EmployeeSurveyResponse> getEmployeeSurvey(){
        EmployeeSurveyResponse employeeSurveyResponse = new EmployeeSurveyResponse();
        List<EmployeeSurvey> employeeSurveys = employeeSurveyService.getAllSubmittedSurvey();
        Map<String, Map<String, Long>> mapObject = new HashMap<>();
        resourceAvailabilityIndex(employeeSurveys, mapObject);
        log.info("mapObject" + mapObject);
        employeeSurveyResponse.setEmployeeSurveys(employeeSurveys);
        employeeSurveyResponse.setAdditionalData(mapObject);
        return new ResponseEntity(employeeSurveyResponse, HttpStatus.OK);
    }

    @GetMapping("/employee/survey-exist")
    public ResponseEntity<EmployeeSurvey> checkEmployeeSurveyExists(@RequestParam("emailAddress") String emailAddress){
        EmployeeSurvey employeeSurvey = employeeSurveyService.checkAlreadySubmittedSurvey(emailAddress);
        log.info("emailAddress" + emailAddress);
        return new ResponseEntity(employeeSurvey, HttpStatus.OK);
    }
    private void resourceAvailabilityIndex(List<EmployeeSurvey> employeeSurveys, Map<String, Map<String, Long>> mapObject) {
        Map<String, Long> resourceAvailabilityMap = employeeSurveys.stream().collect(Collectors.groupingBy(k -> k.getResourceAvailability(), Collectors.counting()));
        Map<String, Long> workCultureMap = employeeSurveys.stream().collect(Collectors.groupingBy(k -> (k.getWorkCulture().replaceAll("\\s","")), Collectors.counting()));
        Map<String, Long> teaQualityMap = employeeSurveys.stream().collect(Collectors.groupingBy(k -> k.getTeaQuality().replaceAll("\\s",""), Collectors.counting()));
        Map<String, Long> overallSatisfactionMap = employeeSurveys.stream().collect(Collectors.groupingBy(k -> k.getOverallSatisfaction().replaceAll("\\s",""), Collectors.counting()));
        Map<String, Long> responsibilityClarity = employeeSurveys.stream().collect(Collectors.groupingBy(k -> k.getResponsibilityClarity(), Collectors.counting()));
        mapObject.put("RESOURCE_AVAILABILITY", resourceAvailabilityMap);
        mapObject.put("WORK_CULTURE", workCultureMap);
        mapObject.put("TEA_QUALITY", teaQualityMap);
        mapObject.put("OVERALL_SATISFACTION", overallSatisfactionMap);
        mapObject.put("RESPONSIBILITY_CLARITY", responsibilityClarityMapTransform(responsibilityClarity));

        ;
    }

    private Map<String, Long> responsibilityClarityMapTransform(Map<String, Long> responsibilityClarity) {
        Map<String, Long> responsibilityClarityMap = new HashMap<>();
        responsibilityClarityMap.put("NoClarity", responsibilityClarity.get("25"));
        responsibilityClarityMap.put("SomewhatClarity", responsibilityClarity.get("50"));
        responsibilityClarityMap.put("Good", responsibilityClarity.get("75"));
        responsibilityClarityMap.put("VeryMuchClarity", responsibilityClarity.get("100"));
        return responsibilityClarityMap;
    }
}