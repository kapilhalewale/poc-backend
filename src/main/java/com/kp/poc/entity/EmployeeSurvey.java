package com.kp.poc.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee_satisfaction_survey")
@Data
public class EmployeeSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fullName;
    private String emailAddress;
    private String resourceAvailability;
    private String responsibilityClarity;
    private String workCulture;
    private String teaQuality;
    private String overallSatisfaction;
    private String briefComment;
    private Date createdOn;
}