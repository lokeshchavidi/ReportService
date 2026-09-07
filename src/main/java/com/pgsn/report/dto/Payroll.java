package com.pgsn.report.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Payroll {
	private Long id;
	private Long employeeId;
	private String employeeName;
	private Double salary;
	private Double bonus;
	private Double tax;
	private Double netSalary;
	private LocalDate payrollDate;
	
}
