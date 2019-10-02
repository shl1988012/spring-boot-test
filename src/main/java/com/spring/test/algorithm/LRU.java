package com.spring.test.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LRU <K,V>{

    LinkedHashMap<K,V> map;

    private final int MAX_CACHE_SIZE;
    private final float DEFAULT_LOAD_FACTORY = 0.75f;

    public LRU(int cacheSize){
        MAX_CACHE_SIZE = cacheSize;
        //保证cache容量稳定，不会扩容
        int capacity = (int)Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTORY) + 1;
        //accessOrder: true : 代表linkedlist按访问顺序排序，可作为LRU缓存
        //第三个参数设置为false，代表按插入顺序排序，可作为FIFO缓存
        map = new LinkedHashMap<K,V>(capacity, DEFAULT_LOAD_FACTORY, true){
            //当返回true的时候，就会remove其中最久的元素，可以通过重写这个方法来控制缓存元素的删除，
            // 当缓存满了后，就可以通过返回true删除最久未被使用的元素，达到LRU的要求。
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > MAX_CACHE_SIZE;
            }
        };
    }

    public synchronized void put(K key, V value){
       map.put(key,value);
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized  void remove(K key){
        map.remove(key);
    }

    public synchronized Set<Map.Entry<K, V>> getAll() {
        return map.entrySet();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            stringBuilder.append(String.format("%s: %s  ", entry.getKey(), entry.getValue()));
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        LRU<Integer,Integer> lru = new LRU<>(5);
        lru.put(1,1);
        lru.put(2,2);
        lru.put(3,3);
        System.out.println(lru.toString());
        lru.get(1);
        System.out.println(lru.toString());
        lru.put(4,4);
        lru.put(5,5);
        lru.put(6,6);
        System.out.println(lru.toString());
    }
}
