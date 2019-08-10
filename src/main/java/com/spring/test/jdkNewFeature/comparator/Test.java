package com.spring.test.jdkNewFeature.comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test {



    public  static void testList(){

        List<String> city = Arrays.asList("Milan","london", "San Francisco", "Tokyo", "New Delhi");
        //[Milan, london, San Francisco, Tokyo, New Delhi]
        System.out.println(city);

        city.sort(String.CASE_INSENSITIVE_ORDER); //返回不区分大小写的Comparator
        //[london, Milan, New Delhi, San Francisco, Tokyo]
        System.out.println(city);

        city.sort(Comparator.naturalOrder()); //返回按照大小写字母排序的Comparator
        //[Milan, New Delhi, San Francisco, Tokyo, london]
        System.out.println(city);

    }

    public static void testInteger(){
        List<Integer> numbers = Arrays.asList(5,2,7,8,1,33);
        System.out.println(numbers);

        numbers.sort(Comparator.naturalOrder());
        System.out.println(numbers);
    }


    public static void testObject(){

        List<Movie> movies = Arrays.asList(
                new Movie("lord",11.1),
                new Movie("acdc",55.0),
                new Movie("jteewt",78.0),
                new Movie("13dsfg4",34.0)
        );

//        movies.sort(Comparator.comparing(Movie::getName));
//        movies.sort(Comparator.comparingDouble(Movie::getScore).reversed());

        movies.sort((o1, o2) -> {
            if(o1.getScore()< o2.getScore()){
                return 0;
            }
            return -1;
        });

        movies.forEach(System.out::println);
    }



    public static void main(String[] args) {
        testObject();

    }


}
