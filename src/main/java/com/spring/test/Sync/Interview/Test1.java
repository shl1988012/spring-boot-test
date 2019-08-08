package com.spring.test.Sync.Interview;


public class Test1 {

    private final Object lock = new Object();

    private int count = 0;

    class A implements Runnable{

        @Override
        public void run() {
            while(count<100){
                synchronized (lock){
                    if((count & 1) == 0){
                        System.out.println(count);
                        count ++;
                    }

                }
            }
        }
    }


    class B implements Runnable{

        @Override
        public void run() {

            while(count<100){
                synchronized (lock){
                    if((count & 1) == 1){
                        System.out.println(count);
                        count ++;
                    }

                }
            }
        }
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        Thread a = new Thread(test1.new A());
        Thread b = new Thread(test1.new B());
        a.start();
        b.start();
    }
}
