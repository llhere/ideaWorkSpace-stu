package com.chen.movie.domain;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class User {

    private Long id;
    private String userName;
    private String name;
    private short age;
    private BigDecimal balance;

}
