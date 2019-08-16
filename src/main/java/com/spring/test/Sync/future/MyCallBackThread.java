package com.spring.test.Sync.future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.spring.test.model.Entity.EntityDemo;

import java.util.concurrent.*;

public class MyCallBackThread<V extends EntityDemo> implements Callable<V> {

    private V resultEntity;

    public MyCallBackThread(V resultEntity){
        this.resultEntity = resultEntity;
    }

    @Override
    public V call() throws Exception {

        Thread.sleep(1000);

        this.resultEntity.setStatus("1");

        return this.resultEntity;
    }


    public static void main(String[] args) throws Exception{

        MyCallBackThread myCallBackThread = new MyCallBackThread(new EntityDemo());

//      ExecutorService es = Executors.newSingleThreadExecutor();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 5,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(),
                new ThreadFactoryBuilder().setNameFormat("callback-thread-%d").build());
        Future future = executor.submit(myCallBackThread);
        System.out.println(((EntityDemo)future.get()).getStatus() );
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE,TimeUnit.SECONDS);
    }


}
