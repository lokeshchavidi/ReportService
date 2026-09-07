package com.pgsn.report.dto;

import lombok.Data;

@Data
public class Employee {
	private Long id;
	private String employeeName;
	private String email;
	private Double salary;
	private Long departmentId;
	
}
