package com.zhimin115200.test.SkyFavor.service.worker;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class WorkerManager {
    private final static int CORE_POOL_SIZE = 2;
    private final static int MAX_POOL_SIZE = 10;
    private final static int KEEP_ALIVE_TIME = 0;
    private final static int WORK_QUEUE_SIZE = 50;

    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
            TimeUnit.SECONDS, new ArrayBlockingQueue(WORK_QUEUE_SIZE));

    public void process(Worker worker) {
        threadPool.execute(worker);
    }
}
