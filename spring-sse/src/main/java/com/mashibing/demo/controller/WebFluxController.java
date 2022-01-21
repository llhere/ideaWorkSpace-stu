package com.mashibing.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.IntStream;

/**
 * @Deacription WebFlux对于mvc的实现，取值方式，flux向服务器推。需要去掉web的stater
 * @Author chenpengwei
 * @Date 2021/11/5 3:07 下午
 * @Version 1.0
 **/
@RestController
@RequestMapping("flux")
public class WebFluxController {


    /**
     * @description flux提供的request，session，需要去掉web的stater
     * @Param [request, name, session]
     * @return reactor.core.publisher.Mono<java.lang.Object>
     * @author chenpengwei
     * @date 2021/11/5
     */
    @RequestMapping("mono")
    public Mono<Object> getMono(ServerHttpRequest request, String name, WebSession session){

        if(StringUtils.hasText(session.getAttribute("code"))) {

            System.out.println("set值~");
            session.getAttributes().put("code", 250);
        }

        System.out.println("code = " + session.getAttribute("code"));

        return Mono.just("mono");
    }


    /**
     * @description flux数据序列向服务器推送数据，需要去掉web的stater
     * @Param []
     * @return reactor.core.publisher.Flux<java.lang.String>
     * @author chenpengwei
     * @date 2021/11/5
     */
    @GetMapping(value = "fluxPushData", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> fluxPushData(){

        Flux<String> flux = Flux.fromStream(IntStream.range(1, 10).mapToObj(i -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "第" + i + "条数据";
        }));

        //返回时连带里面的方法，给了容器
        return flux;
    }

}
