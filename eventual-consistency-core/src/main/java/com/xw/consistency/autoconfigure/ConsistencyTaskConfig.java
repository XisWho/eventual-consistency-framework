package com.xw.consistency.autoconfigure;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsistencyTaskConfig {

    /**
     * 调度型任务线程池的核心线程数
     */
    public Integer executeCoreSize;
    /**
     * 调度型任务线程池的最大线程数
     */
    public Integer executeMaxSize;
    /**
     * 调度型任务线程池的队列大小
     */
    public Integer executeQueueSize;
    /**
     * 线程池中无任务时线程存活时间，单位：秒
     */
    public Long executeKeepAliveTime;

}
