package com.zhouzy.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**   
* <p>�̳߳�</p>
Javaͨ��Executors�ṩ�����̳߳أ��ֱ�Ϊ��
newCachedThreadPool
    -- ����һ���ɻ����̳߳أ�����̳߳س��ȳ���������Ҫ��
    -- �������տ����̣߳����޿ɻ��գ����½��̡߳�
newFixedThreadPool 
    -- ����һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ���
newScheduledThreadPool 
    -- ����һ�������̳߳أ�֧�ֶ�ʱ������������ִ�С�
newSingleThreadExecutor 
    -- ����һ�����̻߳����̳߳أ���ֻ����Ψһ�Ĺ����߳���ִ������
    -- ��֤����������ָ��˳��(FIFO, LIFO, ���ȼ�)ִ�С�

* @title          - ThreadPool.java 
* @author         - NingZhong.Zeng   
* @date           - 2015��11��26�� ����11:01:32 
*/

public class ThreadPool {


    public static void main(String[] args) {
         cachedThreadPool();
        fixedThreadPool();
        scheduledThreadPool();
        singleThreadExecutor();
    }

    /**
     * -- ����һ���ɻ����̳߳أ�����̳߳س��ȳ���������Ҫ��
       -- �������տ����̣߳����޿ɻ��գ����½��̡߳�
       �ŵ㣺���������Ժ�
       ȱ�㣺������PV�����׳��ڴ�
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
     * -- ����һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ���
     * �ŵ㣺����С
       ȱ�㣺��Ҫ�ȴ�
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
     * -- ����һ�������̳߳أ�֧�ֶ�ʱ������������ִ�С�
     */
    public static void scheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        // ��ʱ2sִ��
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                System.out.println("delay 2 seconds");
            }
        }, 2, TimeUnit.SECONDS);

        // ��ʱ1s��ѭ��3sִ��
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
     * ����һ�����̻߳����̳߳أ���ֻ����Ψһ�Ĺ����߳���ִ������
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