package com.melo.array;

import java.util.concurrent.*;

/**
 * Created by Ablert
 * on 2018/6/13.
 * @author Ablert
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) {
        //创建容器
        final Basket basket = new Basket();

        /**
         * 生产者线程
         */
        class Producer implements Runnable {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("produce apple start");
                        basket.produce();
                        System.out.println("produce apple end");
                        TimeUnit.SECONDS.sleep(3);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 消费者线程
         */
        class Consumer implements Runnable {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("consume apple start");
                        basket.consume();
                        System.out.println("consume apple end");
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        int threadCount = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executorService = new ThreadPoolExecutor(threadCount, threadCount, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3));
        //创建线程
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        executorService.execute(producer);
        executorService.execute(consumer);
        //主线程跑完
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

    }
}
