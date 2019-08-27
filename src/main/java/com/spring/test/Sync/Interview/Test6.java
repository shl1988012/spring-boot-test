package com.spring.test.Sync.Interview;

import java.util.concurrent.*;

public class Test6 {


    //1+2+...+100
    public static int addTo100() throws Exception{

        FutureTask<Integer>task = new FutureTask<>(()->{
            int result =0;
            for(int i =0; i<100; i++){
                result+=i;
            }
            Thread.sleep(5000);
            return result;
        });
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(task);
        return task.get();

    }

    public static void test(){


        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 7/0).exceptionally(e ->{
           e.printStackTrace();
           return -1;

        });

    }

    public static void main(String[] args) throws Exception {
            test();
            Thread.sleep(2000);
        System.out.println("===============");
    }

}
