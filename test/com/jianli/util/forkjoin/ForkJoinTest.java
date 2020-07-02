package com.jianli.util.forkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinTest {

    @Test
    public void test01(){
        Instant start = Instant.now();

        //fork join线程池
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCaculate(0,50000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费时间:" + Duration.between(start,end).toMillis());
    }

    /*
        普通for循环
     */
    @Test
    public void test02(){
        Instant start = Instant.now();

        long sum = 0L;

        for(long i = 0; i < 10000000000L;i++){
            sum += i;
        }

        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费时间为:" + Duration.between(start,end).toMillis());
    }

    /*
    java8并行流
 */
    @Test
    public void test03(){
        Instant start = Instant.now();

        //parallel底层是ForkJoin
        //Java 8 有个公共的ForkJoin池
        LongStream.rangeClosed(0,100000000000L)
                .parallel()
                .reduce(0,Long::sum);

        Instant end = Instant.now();

        System.out.println("耗费时间为:" + Duration.between(start,end).toMillis());
    }
}
