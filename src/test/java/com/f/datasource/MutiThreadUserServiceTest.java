package com.f.datasource;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-29 上午10:59
 **/
public class MutiThreadUserServiceTest {
    /**
     * Generates sequential unique IDs starting with 1, 2, 3, and so on.
     * <p>
     * This class is NOT thread-safe.
     * </p>
     */
    static class BrokenUniqueIdGenerator {
        private long counter = 0;

        public long nextId() {
            return++counter;
        }
    }

    /**
     * Generates sequential unique IDs starting with 1, 2, 3, and so on.
     * <p>
     * This class is thread-safe.
     * </p>
     */
    static class UniqueIdGenerator {
        private final AtomicLong counter = new AtomicLong();

        public long nextId() {
            return counter.incrementAndGet();
        }
    }

    private void test(final int threadCount) throws InterruptedException, ExecutionException {
        final UniqueIdGenerator domainObject = new UniqueIdGenerator();
        Callable<Long> task = new Callable<Long>() {
            @Override
            public Long call() {
                return domainObject.nextId();
            }
        };
        List<Callable<Long>> tasks = Collections.nCopies(threadCount, task);
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<Future<Long>> futures = executorService.invokeAll(tasks);
        List<Long> resultList = new ArrayList<Long>(futures.size());
        // Check for exceptions
        for(Future<Long> future : futures) {
            // Throws an exception if an exception was thrown by the task.
            resultList.add(future.get());
        }
        // Validate the IDs
        Assert.assertEquals(threadCount, futures.size());
        List<Long> expectedList = new ArrayList<Long>(threadCount);
        for(long i =  1; i <= threadCount; i++) {
            expectedList.add(i);
        }
        Collections.sort(resultList);
        Assert.assertEquals(expectedList, resultList);
    }

    @Test
    public void test01() throws InterruptedException, ExecutionException {
        test(1);
    }

    @Test
    public void test02() throws InterruptedException, ExecutionException {
        test(2);
    }

    @Test
    public void test04() throws InterruptedException, ExecutionException {
        test(4);
    }

    @Test
    public void test08() throws InterruptedException, ExecutionException {
        test(8);
    }

    @Test
    public void test16() throws InterruptedException, ExecutionException {
        test(16);
    }

    @Test
    public void test32() throws InterruptedException, ExecutionException {
        test(32);
    }
}
