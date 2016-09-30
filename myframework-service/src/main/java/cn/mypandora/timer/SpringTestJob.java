/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 基于Spring-task注解方式的用例。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Service
public class SpringTestJob {
    @Scheduled(cron = "0 24 16 * * ?")
    public void job1() {
        System.out.println("Spring-task任务进行中...我是注解我是注解...");
    }
}
