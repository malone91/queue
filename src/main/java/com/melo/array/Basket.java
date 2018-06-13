package com.melo.array;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Ablert
 * on 2018/6/13.
 * @author Ablert
 */
public class Basket {

    BlockingQueue<String> basket = new ArrayBlockingQueue<String>(3);

    /**
     * put element to queue
     * @throws InterruptedException
     */
    public void produce() throws InterruptedException {
        basket.put("apple");
        System.out.println(getAppleNum());
    }

    /**
     * take from queue
     * @return
     * @throws InterruptedException
     */
    public String consume() throws InterruptedException {
        String apple = basket.take();
        System.out.println(getAppleNum());
        return apple;
    }

    /**
     * get num from queue
     * @return
     */
    public int getAppleNum() {
        return basket.size();
    }
}
