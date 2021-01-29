package com.spring.test.Sync.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class Tea {


    public static void tea() {

        CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1: 洗水壶");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T1: 烧水");
        });


        CompletableFuture<String> t2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2: 洗茶杯");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2: 拿茶叶");
            return "龙井";
        });

        CompletableFuture<String> t3 = t1.thenCombine(t2, (aVoid, s) -> {

            System.out.println("拿到茶叶，"+ s);
            return "上茶"+ s;
        });

        System.out.println(t3.join());
    }

    public static void main(String[] args) {
        tea();
    }
}
