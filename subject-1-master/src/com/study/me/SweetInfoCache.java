package com.study.me;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SweetInfoCache {

    //redis
    private Map<String,Object> redis = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private boolean cacheValid;

    public Object get(String k){
        //获取读取锁
        lock.readLock().lock();

        Object data = null;
        try {
            //如果redis有值则直接获取
            if (cacheValid){
                redis.get(k);
            }else {
                //防止内存雪崩
                lock.readLock().unlock();
                lock.writeLock().lock();

                try {
                    //去数据库查
                    if (!cacheValid){
                        String name = queryUserName("chen");
                        redis.put("name",name);
                        cacheValid = false;
                    }
                }finally {
                    //降级为读锁
                    lock.readLock().unlock();
                    lock.writeLock().unlock();
                }

            }

        }finally {
            //释放读取锁
            lock.readLock().unlock();
        }


        return data;
    }


    /**
     * 模拟数据库查询数据
     * @param name
     * @return
     */
    public String queryUserName(String name){
        return name;
    }




}
