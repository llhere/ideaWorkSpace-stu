package com.example.demo.model;

import java.io.Serializable;

/**
 * @Deacription 行政库和案例库的实体类
 * @Author chenpengwei
 * @Date 2020/8/13 9:33 上午
 * @Version 1.0
 **/
public class WordEs implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 文件（word）id */
    private Long id;

    /** 文件（word）名称 */
    private String file_name;

    /** 文件（word）位置 */
    private String route;

    /** 文件（word）内容 */
    private String text;

    public WordEs() {
    }

    public WordEs(Long id, String file_name, String route, String text) {
        this.id = id;
        this.file_name = file_name;
        this.route = route;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}


