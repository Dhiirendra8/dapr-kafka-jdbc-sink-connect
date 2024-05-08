package com.learn.dapr.json.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Order {
    private Integer id;
    private String name;
    private Float price;
}
