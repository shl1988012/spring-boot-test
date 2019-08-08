package com.spring.test.Sync.Interview;


public class Test2 {


    class A implements Runnable{

        @Override
        public void run() {
            for(int i = 0 ; i< 101; i+=2){
                print(i);
            }
        }
    }

    private synchronized void print(int count){
        notifyAll();
        System.out.println(count);
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    class B implements Runnable{

        @Override
        public void run() {
            for(int i = 1 ; i< 100; i+=2){
                print(i);
            }
        }
    }

    public static void main(String[] args) {
        Test2 test1 = new Test2();
        Thread a = new Thread(test1.new A());
        Thread b = new Thread(test1.new B());
        a.start();
        b.start();
    }
}
