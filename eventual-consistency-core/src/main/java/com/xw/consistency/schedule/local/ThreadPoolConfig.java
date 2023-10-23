package com.xw.consistency.schedule.local;

import com.xw.consistency.autoconfigure.ConsistencyTaskConfig;
import com.xw.consistency.entity.ConsistencyTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class ThreadPoolConfig {

    private final static String CONSISTENCY_TASK_THREAD_POOL_PREFIX = "CTThreadPool_";

    @Autowired
    private ConsistencyTaskConfig consistencyTaskConfig;

    /**
     * 一致性任务执行的并行任务执行线程池
     * @return 并行任务线程池
     */
    @Bean
    public CompletionService<ConsistencyTaskInstance> consistencyTaskPool() {
        LinkedBlockingQueue<Runnable> asyncConsistencyTaskThreadPoolQueue =
                new LinkedBlockingQueue<>(consistencyTaskConfig.getExecuteQueueSize());
        ThreadPoolExecutor asyncReleaseResourceExecutorPool = new ThreadPoolExecutor(
                consistencyTaskConfig.getExecuteCoreSize(),
                consistencyTaskConfig.getExecuteMaxSize(),
                consistencyTaskConfig.getExecuteKeepAliveTime(),
                TimeUnit.SECONDS,
                asyncConsistencyTaskThreadPoolQueue,
                createThreadFactory(CONSISTENCY_TASK_THREAD_POOL_PREFIX)
        );
        return new ExecutorCompletionService<>(asyncReleaseResourceExecutorPool);
    }

    /**
     * 创建线程池工厂
     * @param threadPoolPrefix 线程池前缀
     * @return 线程池工厂
     */
    private ThreadFactory createThreadFactory(String threadPoolPrefix) {
        return new ThreadFactory() {
            private AtomicInteger threadIndex = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, threadPoolPrefix + this.threadIndex.incrementAndGet());
            }
        };
    }

}
