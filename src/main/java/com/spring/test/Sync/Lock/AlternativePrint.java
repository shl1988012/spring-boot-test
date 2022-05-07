package com.spring.test.Sync.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hlshi3
 * @date 2022/4/20 19:58
 */
public class AlternativePrint {

    private ReentrantLock lock = new ReentrantLock();

    private Condition conditionA = lock.newCondition();

    private Condition conditionB = lock.newCondition();

    private Condition conditionC = lock.newCondition();

    private volatile int number = 1;

    public void loopA(){
        lock.lock();
        try{
            if(number !=1){
                conditionA.await();
            }
            System.out.print(Thread.currentThread().getName());
            number =2;
            // 唤醒下一个打印线程
            conditionB.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void loopB(){
        lock.lock();
        try {
            if (number != 2){
                conditionB.await();
            }

            System.out.print(Thread.currentThread().getName());

            number = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC(){
        lock.lock();
        try {
            if (number != 3){
                conditionC.await();
            }
            System.out.print(Thread.currentThread().getName());
            number = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        AlternativePrint alternativePrint = new AlternativePrint();
        new Thread("A"){
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    alternativePrint.loopA();
                }
            }
        }.start();

        new Thread("B"){
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    alternativePrint.loopB();
                }
            }
        }.start();

        new Thread("C"){
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    alternativePrint.loopC();
                }
            }
        }.start();
    }


}
