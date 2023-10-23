package com.xw.consistency.schedule.local;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LocalScheduler {

    @Autowired
    private LocalExecutor localExecutor;

    @Scheduled(fixedRate = 30 * 1000L)
    public void schedule() throws Exception {
        localExecutor.execute();
    }

}
