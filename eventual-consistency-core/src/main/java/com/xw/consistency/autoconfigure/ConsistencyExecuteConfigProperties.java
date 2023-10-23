package com.xw.consistency.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "consistency.execute.pool")
public class ConsistencyExecuteConfigProperties {

    /**
     * 调度型任务线程池的核心线程数
     */
    public Integer coreSize = 5;
    /**
     * 调度型任务线程池的最大线程数
     */
    public Integer maxSize = 5;
    /**
     * 调度型任务线程池的队列大小
     */
    public Integer queueSize = 100;
    /**
     * 线程池中无任务时线程存活时间
     */
    public Long keepAliveTime = 60L;

}
