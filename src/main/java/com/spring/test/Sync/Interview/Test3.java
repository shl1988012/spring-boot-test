package com.spring.test.Sync.Interview;


/*有两个线程，分别为ThreadA和ThreadB。
        ThreadA按顺序打印1,2,3,...100,以此类推。
        ThreadB按顺序打印-1,-2,-3,...-100,以此类推。
        请实现两个线程交替工作，使控制台交替输出。每次输出一个数值，打印
        1,-1,2,-2....100,-100*/

public class Test3 {

    class A implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i < 101; i++) {
                print(i);
            }
        }
    }

    public synchronized void print(int i) {
        notifyAll();
        System.out.println(i);
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class B implements Runnable {
        @Override
        public void run() {
            for (int i = -1; i > -101; i--) {
                print(i);
            }
        }
    }

    public static void main(String[] args) {

        Test3 test3 = new Test3();
        Thread thread1 = new Thread(test3.new A());
        Thread thread2 = new Thread(test3.new B());

        thread1.start();
        thread2.start();
    }
}
