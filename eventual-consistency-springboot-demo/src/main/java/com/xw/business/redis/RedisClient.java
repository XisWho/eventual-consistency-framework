package com.xw.business.redis;

import com.xw.business.dto.OrderDTO;
import com.xw.consistency.annotation.ConsistencyTask;
import org.springframework.stereotype.Component;

@Component
public class RedisClient {

    @ConsistencyTask()
    public void store(OrderDTO orderInfoDTO) {
    }

    @ConsistencyTask()
    public void store() {
    }

    @ConsistencyTask
    public void store(OrderDTO orderInfoDTO, Integer i) {
        int result = 1/0;
    }

    @ConsistencyTask
    public void store(OrderDTO orderInfoDTO, int i) {
        int result = 1/0;
    }

}
