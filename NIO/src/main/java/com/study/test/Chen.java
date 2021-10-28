package com.study.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Deacription
 * @Author chenpengwei
 * @Date 2019/11/29 下午 3:57
 * @Version 1.0
 **/
public class Chen {

   static  AtomicInteger val = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    val.getAndAdd(3);
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(val.get());

    }

}
