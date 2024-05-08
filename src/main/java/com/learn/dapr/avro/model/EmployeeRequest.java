package com.learn.dapr.avro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeRequest {
    private String topic;
    private String pubsubName;
    private EmployeeModel employee;
}
