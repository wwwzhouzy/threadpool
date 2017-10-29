package com.zhouzy.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**   
* <p>线程池</p>
Java通过Executors提供四种线程池，分别为：
newCachedThreadPool
    -- 创建一个可缓存线程池，如果线程池长度超过处理需要，
    -- 可灵活回收空闲线程，若无可回收，则新建线程。
newFixedThreadPool 
    -- 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
newScheduledThreadPool 
    -- 创建一个定长线程池，支持定时及周期性任务执行。
newSingleThreadExecutor 
    -- 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，
    -- 保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

* @title          - ThreadPool.java 
* @author         - NingZhong.Zeng   
* @date           - 2015年11月26日 上午11:01:32 
*/

public class ThreadPool {


    public static void main(String[] args) {
         cachedThreadPool();
        fixedThreadPool();
        scheduledThreadPool();
        singleThreadExecutor();
    }

    /**
     * -- 创建一个可缓存线程池，如果线程池长度超过处理需要，
       -- 可灵活回收空闲线程，若无可回收，则新建线程。
       优点：长度伸缩性好
       缺点：开销大，PV大容易吃内存
     */
    public static void cachedThreadPool(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }

    /**
     * -- 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     * 优点：开销小
       缺点：需要等待
     */
    public static void fixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * -- 创建一个定长线程池，支持定时及周期性任务执行。
     */
    public static void scheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        // 定时2s执行
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                System.out.println("delay 2 seconds");
            }
        }, 2, TimeUnit.SECONDS);

        // 定时1s，循环3s执行
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("delay 3 seconds, and excute every 1 seconds");
            }
        }, 3, 1, TimeUnit.SECONDS);

    }

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，
     */
    public static void singleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}