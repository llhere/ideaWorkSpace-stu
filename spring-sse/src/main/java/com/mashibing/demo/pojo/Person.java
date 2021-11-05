package com.mashibing.demo.pojo;

/**
 * @Deacription
 * @Author chenpengwei
 * @Date 2021/11/5 2:05 下午
 * @Version 1.0
 **/
public class Person {

    private String id;
    private String name;

    public Person() {
    }

    public String getId() {
        return id;
    }

    public Person setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }
}
