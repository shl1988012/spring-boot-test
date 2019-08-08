package com.spring.test.Sync;

import java.util.Vector;

public class Test7 {

    private final Object o = new Object();
    private volatile long count = 0;

    void add10k() {
        int idx = 0;
        while (idx++ < 500) {
//            synchronized (o){
//                count++;
//            }
//            setCount();
            setCount(count);
        }
    }

    synchronized void setCount(long count1) {
        count1++;
        this.count = count1;
    }

    synchronized void setCount() {
        count++;
    }

    public static void main(String[] args) throws Exception {

        Test7 test = new Test7();
        Thread thread1 = new Thread(() -> test.add10k());

        Thread thread2 = new Thread(() -> test.add10k());

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(test.count);
    }

}
