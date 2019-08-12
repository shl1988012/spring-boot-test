package com.spring.test.Sync.Lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCache<K,V> {


    final Map<K,V> map = new HashMap();

    //分段 按key的前缀拆分
    final Map<K, Map<K, V>> segMap = new HashMap<>();

    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    //read
    final Lock r = rwl.readLock();
    //write
    final Lock w = rwl.writeLock();

    V get(K key){
        V v = null;
        r.lock();
        try{
          v =  map.get(key);
        }finally {
            r.unlock();
        }
        if(v != null){
            return v;
        }
        w.lock();
        try{
            v = map.get(key);
            if(v == null){
                //查询数据库获取v的值
                v=(V)"";
                map.put(key, v);
            }
        }finally{
            w.unlock();
        }
        return v;
    }

//锁的升级    ReentrantReadWriteLock不支持升级  只支持降级
    V get1(K key){
        V v = null;
        r.lock();
        try{
            v =  map.get(key);
            if(v == null){
                w.lock();
                try{
                    //再次验证并更新缓存
                    //略
                }finally{
                    w.unlock();
                }
            }
        }finally {
            r.unlock();
        }
        return v;
    }




    V put(K key, V value){
        w.lock();
        try{
            return map.put(key, value);
        }finally {
            w.unlock();
        }
    }


}
