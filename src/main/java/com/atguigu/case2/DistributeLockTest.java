package com.atguigu.case2;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

/**
 * @description:
 * @author: gxl
 * @createDate: 2021/8/31 14:41
 */
public class DistributeLockTest {
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {

        final DistributeLock lock1 = new DistributeLock();

        final DistributeLock lock2 = new DistributeLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock1.zklock();
                    System.out.println("线程1启动，获取到锁");

                    Thread.sleep(5000);

                    lock1.unZklock();
                    System.out.println("线程1 释放锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock2.zklock();
                    System.out.println("线程2启动，获取到锁");

                    Thread.sleep(5000);

                    lock2.unZklock();
                    System.out.println("线程2 释放锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
