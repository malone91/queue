package com.melo.waitnotify;

import java.util.Vector;

public class WaitNotifyTest {
    public static void main(String[] args) {
        Vector<Integer> pool = new Vector<>();
        Producer producer = new Producer(pool, 10);
        Consumer consumer = new Consumer(pool);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    static class Producer implements Runnable {

        private Vector<Integer> pool;
        private Integer size;

        public Producer(Vector<Integer> pool, int size) {
            this.pool = pool;
            this.size = size;
        }

        @Override
        public void run() {
            for (;;) {
                try {
                    System.out.println("生产一个商品");
                    produce(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void produce(int i) throws InterruptedException {
            while (pool.size() == size) {
                synchronized (pool) {
                    System.out.println("生产者等待消费者消费商品，当前商品数量为： " + pool.size());
                    pool.wait();
                }
            }
            synchronized (pool) {
                pool.add(i);
                pool.notifyAll();
            }
        }
    }

    static class Consumer implements Runnable {

        private Vector<Integer> pool;

        public Consumer(Vector<Integer> pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            for (;;) {
                try {
                    System.out.println("消费一个商品");
                    consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void consume() throws InterruptedException {
            synchronized (pool) {
                while (pool.isEmpty()) {
                    System.out.println("消费者等待生产者生产商品，当前商品数量为： " + pool.size());
                    pool.wait();
                }
            }
            synchronized (pool) {
                pool.remove(0);
                pool.notifyAll();
            }
        }
    }
}
