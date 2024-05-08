package com.learn.dapr.avro.controller;

import com.learn.dapr.avro.model.EmployeeModel;
import com.learn.dapr.avro.model.EmployeeRequest;
import com.learn.dapr.avro.service.EmployeePublishEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeePublishEventService daprPublishEventService;

    @PostMapping("/employees/publish")
    public String publishEmployeeEvents(@RequestBody EmployeeRequest request) {
        EmployeeModel employeeModel = request.getEmployee();
        log.info("EmployeeModel: " + employeeModel);
        daprPublishEventService.publishEventUsingMetadata(request.getPubsubName(), request.getTopic(), employeeModel);
//        log.info("Published Employee data: " + employee.toString());
        return "Published";
    }
}
