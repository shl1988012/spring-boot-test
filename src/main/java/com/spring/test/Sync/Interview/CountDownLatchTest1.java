package com.spring.test.Sync.Interview;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchTest1 {


    private final static int threadCount = 200;

    static ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception{

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        long start = System.currentTimeMillis();
        for(int i = 0 ; i< threadCount; i++){
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    Thread.sleep(1000);
                    log.info("{}", threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        long end = System.currentTimeMillis();
        log.info("cost :{} milliseconds", end - start);

        exec.shutdown();
//        exec.shutdownNow();
    }

}
