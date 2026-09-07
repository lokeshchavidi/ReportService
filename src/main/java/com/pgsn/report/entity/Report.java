package com.pgsn.report.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private String employeeName;

    private String departmentName;

    private Double salary;

    private Double bonus;

    private Double tax;

    private Double netSalary;

    private LocalDateTime generatedOn;
}