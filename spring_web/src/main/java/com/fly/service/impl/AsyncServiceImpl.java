package com.fly.service.impl;

import com.fly.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public class AsyncServiceImpl implements AsyncService {

    @Async
    @Override
    public void Hello() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据正在处理.......");
    }
}
