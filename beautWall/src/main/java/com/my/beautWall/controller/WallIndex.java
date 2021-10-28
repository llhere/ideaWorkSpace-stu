package com.my.beautWall.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class WallIndex {


    @RequestMapping("wall")
    public String index(){
        System.err.println(123);
        return "index";
    }

}
