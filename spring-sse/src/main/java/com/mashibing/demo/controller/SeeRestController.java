package com.mashibing.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Deacription 长链接的SseEmitter
 * @Author chenpengwei
 * @Date 2021/11/1 3:40 下午
 * @Version 1.0
 **/
@RestController
@RequestMapping("sse")
public class SeeRestController {

    //存储链接Map，用于推送消息
    private static Map<String, SseEmitter> sseCache = new ConcurrentHashMap<>();


    /**
     * @description 初始化链接信息
     * @Param [id]
     * @return org.springframework.web.servlet.mvc.method.annotation.SseEmitter
     * @author chenpengwei
     * @date 2021/11/1
     */
    @RequestMapping("subscribe")
    public SseEmitter subscribe(String id){

        //创建返回对象
        SseEmitter sseEmitter = new SseEmitter(3600000L);
        //添加进
        sseCache.put(id, sseEmitter);
        //超时回调，删除建立的链接
        sseEmitter.onTimeout(() -> sseCache.remove(id));
        //结束之后回调，打印信息
        sseEmitter.onCompletion(() -> System.out.println("完成"));

        return sseEmitter;
    }


    /**
     * @description 根据SseEmitter的id主动推送消息
     * @Param [id, content]
     * @return java.lang.String
     * @author chenpengwei
     * @date 2021/11/1
     */
    @RequestMapping("push")
    public String push(String id, String content) throws IOException {

        //获取指定的链接
        SseEmitter sseEmitter = sseCache.get(id);
        //不为空推送消息
        if (null != sseEmitter) {
            sseEmitter.send(content);
        }

        return "push成功";
    }

    /**
     * @description 断开连接
     * @Param [id]
     * @return java.lang.String
     * @author chenpengwei
     * @date 2021/11/1
     */
    @GetMapping(path = "over")
    public String over(String id) {
        SseEmitter sseEmitter = sseCache.get(id);
        // 执行完毕，断开连接
        if (sseEmitter != null) {
            sseEmitter.complete();
            sseCache.remove(id);
        }
        return "over成功";
    }


    /**
     * @description 向全部链接推送消息
     * @Param [content]
     * @return java.lang.String
     * @author chenpengwei
     * @date 2021/11/1
     */
    @GetMapping(path = "/push-all")
    public String pushAll(String content) throws IOException {

        //遍历全部链接
        for (String s : sseCache.keySet()) {
            SseEmitter sseEmitter = sseCache.get(s);
            // 发送消息
            if (sseEmitter != null) {
                sseEmitter.send(content);
            }
        }

        return "push-all成功";
    }

}
