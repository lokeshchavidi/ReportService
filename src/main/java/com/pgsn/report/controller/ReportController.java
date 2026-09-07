package com.pgsn.report.controller;

import org.springframework.web.bind.annotation.*;

import com.pgsn.report.entity.Report;
import com.pgsn.report.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @PostMapping("/generate/{employeeId}")
    public Report generateReport(
            @PathVariable Long employeeId){

        return service.generateReport(employeeId);

    }

    @GetMapping("/{id}")
    public Report getReport(
            @PathVariable Long id){

        return service.getReport(id);

    }

}