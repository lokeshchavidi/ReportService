package com.pgsn.report.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pgsn.report.dto.Department;
import com.pgsn.report.dto.Employee;
import com.pgsn.report.dto.Payroll;
import com.pgsn.report.entity.Report;
import com.pgsn.report.exception.ResourceNotFoundException;
import com.pgsn.report.repository.ReportRepository;

@Service
public class ReportService {

    private final ReportRepository repository;
    private final RestTemplate restTemplate;

    public ReportService(ReportRepository repository,
                         RestTemplate restTemplate) {

        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    
    public Report generateReport(Long employeeId) {

        Employee employee = restTemplate.getForObject(
                "http://localhost:8081/employees/" + employeeId,
                Employee.class);

        if(employee == null) {
            throw new ResourceNotFoundException("Employee not found");
        }

        Department department = restTemplate.getForObject(
                "http://localhost:8082/departments/"
                        + employee.getDepartmentId(),
                Department.class);

        Payroll payroll = restTemplate.getForObject(
                "http://localhost:8083/payroll/"
                        + employeeId,
                Payroll.class);

        Report report = new Report();

        report.setEmployeeId(employee.getId());
        report.setEmployeeName(employee.getEmployeeName());
        report.setDepartmentName(department.getDepartmentName());

        report.setSalary(payroll.getSalary());
        report.setBonus(payroll.getBonus());
        report.setTax(payroll.getTax());
        report.setNetSalary(payroll.getNetSalary());

        report.setGeneratedOn(LocalDateTime.now());

        return repository.save(report);

    }

    public Report getReport(Long id) {

        return repository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "Report not found"));

    }

}