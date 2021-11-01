package com.mashibing.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Deacription 模拟长链接（前台轮询）
 * @Author chenpengwei
 * @Date 2021/10/25 4:31 下午
 * @Version 1.0
 **/
@RestController
public class SseController {


    /**
     * @description 前台发送请求，后台服务器像前台推送
     * @Param []
     * @return java.lang.String
     * @author chenpengwei
     * @date 2021/10/25
     */
    @RequestMapping(value = "sse", produces = "text/event-stream;charset=utf-8;")
    public String Sse(){
        return "data:sse" + System.currentTimeMillis() + "\n\n";
    }

}
