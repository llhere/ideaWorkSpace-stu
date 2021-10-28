package com.study.threadpool.showDemo;

import com.study.threadpool.p2.FixedSizeThreadPool;

public class TestThreadPool {

    public static void main(String[] args) {
        FixedSizeThreadPool pool = new FixedSizeThreadPool(3,6);
        for(int i=0;i<6;i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("一个线程被放到了我们的仓库中");
                    try {
                        Thread.sleep(2500l);
                    } catch (InterruptedException e) {
                        System.out.println("有一个线程被唤醒了");
                    }
                }
            });
        }
    }
}
