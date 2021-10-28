package com.study.me;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SweetHashMap {

    private final Map<String,Object> map = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock();
    private final Lock w = lock.writeLock();


    /**
     * 获取元素
     * @param k
     * @return
     */
    public Object get(String k){
        r.lock();
        try {
            return map.get(k);
        }finally {
            r.unlock();
        }
    }


    /**
     * 存放元素
     * @param k
     * @param v
     */
    public void put(String k, Object v){
        w.lock();
        try {
            map.put(k,v);
        }finally {
            w.unlock();
        }
    }


    /**
     * 获取所有主键
     * @return
     */
    public Object[] allkey(){
        r.lock();

        try {
           return map.entrySet().toArray();
        }finally {
            r.unlock();
        }
    }


    /**
     * 清空所有元素
     */
    public void clear(){
        w.lock();

        try {
            map.clear();
        }finally {
            w.unlock();
        }


    }

}
