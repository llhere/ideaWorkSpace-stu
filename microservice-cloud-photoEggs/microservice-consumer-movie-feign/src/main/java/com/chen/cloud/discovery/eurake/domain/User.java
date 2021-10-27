package com.chen.cloud.discovery.eurake.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class User {
    private Long id;
    private String username;
    private String name;
    private Short age;
    private BigDecimal balance;
}
