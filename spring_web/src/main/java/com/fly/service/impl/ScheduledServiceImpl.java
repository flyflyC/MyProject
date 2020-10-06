package com.fly.service.impl;

import com.fly.service.ScheduledService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduledServiceImpl implements ScheduledService {

    /**
     * 在一个特定的时间执行这个方法
     * @Scheduled(cron = "0 30 17 * * ?") 秒 分 时 日 月 周几
     */
    @Scheduled(cron = "0 36 17 * * ?")
    @Override
    public void sayHello() {
        System.out.println("在一个特定的时间执行(17:38)这个方法");
    }
    @Scheduled(cron = "0/20 * * * * ?")
    @Override
    public void sayHello2() {
        System.out.println("每20秒执行一次：hello");
    }
}
