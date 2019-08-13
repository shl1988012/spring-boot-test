package com.spring.test.springNewFeature.webflux;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.stream.Stream;

public class FluxTest {


    public static void main(String[] args) {

        Flux<String> flux1 = Flux.just("one", "two", "three");

        Flux<String> flux2 = Flux.fromIterable(Arrays.asList("one", "two", "three"));

        Flux<String> flux3 = Flux.fromStream(Stream.of("one", "two", "three"));

        Flux.fromStream(Stream.of("one", "two", "three")).parallel(5).runOn(Schedulers.parallel()).subscribe(s -> System.out.println(s));

    }
}
