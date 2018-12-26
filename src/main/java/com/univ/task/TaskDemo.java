package com.univ.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author univ
 * @datetime 2018/12/26 4:28 PM
 * @description
 */

/**
 * 别忘了使任务类为bean
 */
@Component
public class TaskDemo {

    /**
     * 每秒执行一次，默认用的是同一个线程
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void sayHello() {
        System.out.println("sayHello当前线程：" + Thread.currentThread().getName());
    }

    @Scheduled(cron = "0/10 * * * * *")
    public void sayHello2() {
        System.out.println("sayHello2当前线程：" + Thread.currentThread().getName());
    }

    @Scheduled(cron = "0/10 * * * * *")
    public void sayHello3() {
        System.out.println("sayHello3当前线程：" + Thread.currentThread().getName());
    }
    //-------------------------任务默认是串行执行的，上面三个任务会是同一个线程执行，且执行完一个再执行下一个------------------------





}
