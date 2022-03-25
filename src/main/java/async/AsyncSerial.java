package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Threads    Task      Time
 * 2          1         4000
 * 4          1         4000
 * 2          10        40000
 * 4          10        40000
 * 单条异步串行链没有价值，但多条异步串行链有价值
 */
public class AsyncSerial {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(4);

        long begin = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            create(executor).get();
        }

        System.out.println(System.currentTimeMillis() - begin);

        executor.shutdown();
    }

    static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static CompletableFuture<Integer> create(ExecutorService executor) {
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() -> {
            int temp = 1;
            System.out.println(Thread.currentThread().getName() + " begin compute and temp initial with : " + temp);
            sleep(1);
            System.out.println(Thread.currentThread().getName() + " doing request!");
            sleep(1);
            System.out.println(Thread.currentThread().getName() + " done request");
            temp += 1;
            System.out.println(Thread.currentThread().getName() + " complete compute and return temp : " + temp);
            return temp;
        }, executor);

        task = task.thenApplyAsync((result) -> {
            System.out.println(Thread.currentThread().getName() + " begin compute");
            sleep(1);
            System.out.println(Thread.currentThread().getName() + " doing request!");
            sleep(1);
            System.out.println(Thread.currentThread().getName() + " done request");
            int temp = result;
            temp += 1;
            System.out.println(Thread.currentThread().getName() + " complete compute and return temp : " + temp);
            return temp;
        }, executor);
        return task;
    }

}
