package com.xw.consistency.autoconfigure;

import com.xw.consistency.util.DefaultValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        ConsistencyExecuteConfigProperties.class
})
public class ConsistencyAutoConfiguration {

    /**
     * 执行调度任务的线程池的配置
     */
    @Autowired
    private ConsistencyExecuteConfigProperties executeConfigProperties;

    @Bean
    public ConsistencyTaskConfig consistencyTaskConfig() {
        validateConfig();

        return ConsistencyTaskConfig.builder()
                .executeCoreSize(DefaultValueUtils.getOrDefault(executeConfigProperties.getCoreSize(), 5))
                .executeMaxSize(DefaultValueUtils.getOrDefault(executeConfigProperties.getMaxSize(), 5))
                .executeQueueSize(DefaultValueUtils.getOrDefault(executeConfigProperties.getQueueSize(), 100))
                .executeKeepAliveTime(DefaultValueUtils.getOrDefault(executeConfigProperties.getKeepAliveTime(), 60L))
                .build();
    }

    private void validateConfig() {
        // 对executeConfigProperties进行校验
    }

}
