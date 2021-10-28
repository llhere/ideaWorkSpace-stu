package com.study.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Deacription
 * @Author chenpengwei
 * @Date 2019/11/29 下午 5:12
 * @Version 1.0
 **/
public class Chen2 {

    static AtomicInteger val = new AtomicInteger(0);
    static int vals = 0;

    final static ReentrantReadWriteLock rw = new ReentrantReadWriteLock();

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000000; j++) {

                    add();

                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(vals);
        System.out.println(System.currentTimeMillis() - start);
    }

    public static synchronized void add() {
        //val.incrementAndGet();
        vals++;
    }


}
