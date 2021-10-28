package com.study.threadpool.p2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedSizeThreadPool{

     //思考：手写一个线程池需要什么东西？

    //1.需要一个仓库(会所的前台)
    private BlockingQueue<Runnable> blockingQueue;

    //2.需要一个服务组织(也就是将我们服务人员放到一起)
    private List<Thread> workers;

    //3.需要服务人员
    public  static  class Worker extends  Thread{
        private FixedSizeThreadPool pool;

        public Worker(FixedSizeThreadPool pool) {
            this.pool = pool;
        }


        @Override
        public void run() {
           //开始服务
            while (this.pool.isWorking||this.pool.blockingQueue.size()>0){
                Runnable task = null;

                try {
                    if(this.pool.isWorking) {
                        task = this.pool.blockingQueue.take();
                    }else{
                        task = this.pool.blockingQueue.poll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(task!=null){
                    task.run();
                    System.out.println("线程:"+Thread.currentThread().getName()+"执行完毕");
                }
            }
        }
    }



    //4.会所开张我们需要前台的容量
   public FixedSizeThreadPool(int poolSize,int taskSize){
        if(poolSize<=0||taskSize<=0)
            throw new IllegalArgumentException("非法参数");

        this.blockingQueue = new LinkedBlockingQueue<>(taskSize);
        //this.workers = Collections.synchronizedList(new ArrayList<>());

        for(int i=0;i<poolSize;i++){
            Worker worker = new Worker(this);
            worker.start();
            workers.add(worker);
        }
   }
    //5.需要客人引导方式(非阻塞)
    public boolean submit(Runnable task){
        if(this.isWorking) {
            return this.blockingQueue.offer(task);
        }else{
            return  false;
        }
    }

    //6.需要客人引导方式(阻塞)
    public void execute(Runnable task) throws InterruptedException {
        this.blockingQueue.put(task);
    }

    //7.需要一个关闭方法(会所关门)
    //a.关闭的时候，前台--停止引导客人进入
    //b.关闭的时候，还有客人在排队--我们要服务完
    //c.关闭的时候，服务人员去引导客人，没有客人就不再阻塞了。
    //d.关闭的时候，万一已经有服务人员阻塞了，就中断它
    private volatile  boolean isWorking = true;
    public void shutDown(){
        this.isWorking = false;

        for(Thread thread:workers){
            if(thread.getState().equals(Thread.State.WAITING)||
                    thread.getState().equals(Thread.State.BLOCKED)){
                thread.interrupt();
            }
        }

    }

    public static void main(String[] args) {
        FixedSizeThreadPool pool = new FixedSizeThreadPool(3,6);
        for(int i=0;i<6;i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("一个线程被放入到我们的仓库中...");
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        System.out.println("======被唤醒=====");
                    }
                }
            });
        }
        pool.shutDown();
    }
}