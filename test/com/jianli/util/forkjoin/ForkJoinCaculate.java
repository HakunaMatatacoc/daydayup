package com.jianli.util.forkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinCaculate extends RecursiveTask<Long> {

    /**
     *  CPU利用率高
     */
    public static final long serialVersionUID = 134656970987L;

    private long start;
    private long end;

    private static final long THRESHOLD = 10000L;

    ForkJoinCaculate(long start, long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if(length <= THRESHOLD){
            long sum = 0;

            for(long i = start;i <= end; i++){
                sum += i;
            }

            return sum;
        }else {
            long middle = (start + end) / 2;

            ForkJoinCaculate left = new ForkJoinCaculate(start,middle);
            left.fork();//拆分子任务同时压入线程队列

            ForkJoinCaculate right = new ForkJoinCaculate(middle + 1, end);
            right.fork();

            return left.join() + right.join();
        }
    }
}
