package com.chen.cloud.user.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nodename;

    @Column
    private String name;
}
