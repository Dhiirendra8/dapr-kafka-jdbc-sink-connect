package com.learn.dapr.avro.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmployeeModel {
    private int id;
    private String firstName;
    private String lastName;
}
