package com.spring.test.Sync.CompletableFuture;

import lombok.extern.slf4j.Slf4j;

import javax.naming.Name;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author hlshi3
 * @date 2020/5/14 9:40
 */
@Slf4j
public class CompletableHandle {


    public static void runAsyncTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("I'll run in a separate thread than the main thread.");
        });
        future.get();
    }


    public static void supplyAsyncTest() throws Exception{
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "\"I'll run in a separate thread than the main thread.\"";
        });
       String msg =  future.get();
        System.out.println(msg);
    }


    public static void thenApplyTest() throws Exception{
//        CompletableFuture<String> yourNameFuture = CompletableFuture.supplyAsync(() -> "herry");
//        CompletableFuture<String> greetingFuture = yourNameFuture.thenApply(name -> "hello "+ name+" , welcome back to cisco");
//        System.out.println(greetingFuture.get());

        CompletableFuture<String> greetingFuture = CompletableFuture.supplyAsync(() -> "herry")
                .thenApply(name ->"hello "+ name)
                .thenApply(s -> s+", welcome back to cisco");

        System.out.println(greetingFuture.get());
    }

    public static void thenAcceptTest() throws Exception{

        CompletableFuture<Void> accept = CompletableFuture.supplyAsync(() -> "herry")
                                                    .thenAccept(s -> System.out.println(s+", welcome back"));

        accept.get();

    }

    public static CompletableFuture<String> downloadWebPage(String pageLink){
        return CompletableFuture.supplyAsync(() -> {
            return pageLink;
        });
    }
    public static void allofTest() throws Exception{

        List<String> webPageLinks = Arrays.asList("aaa","abc","addd","acr","we");
        List<CompletableFuture<String>>pageContentFutures =  webPageLinks.stream().map(webPageLink -> downloadWebPage(webPageLink))
                                                            .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                pageContentFutures.toArray(new CompletableFuture[pageContentFutures.size()]));
        allFutures.get();
    }


    public static void anyofTest() throws Exception{
    }


    public static void testError(){
        CompletableFuture.supplyAsync(() -> {
            // Code which might throw an exception
            return "Some result";
        }).exceptionally(throwable -> {
            log.error("1 error");
            return null;
        }).thenApply(result -> {
            return "processed result";
        }).thenApply(result -> {
            return "result after further processing";
        }).thenAccept(result -> {
            // do something with the final result
        });
    }

    public static void handleTest() throws Exception{
        Integer age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if(age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if(age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).handle((res, ex) -> {
            if(ex != null) {
                log.error("Oops! We have an exception - " + ex.getMessage());
                return "Unknown!";
            }
            return res;
        });

        System.out.println("Maturity : " + maturityFuture.get());
    }

    public static void main(String[] args) throws Exception {

        allofTest();
    }
}
