package com.xw.consistency.service;

import com.xw.consistency.entity.ConsistencyTaskInstance;

import java.util.List;

public interface TaskService {

    void addTask(ConsistencyTaskInstance task);

    List<ConsistencyTaskInstance> queryByStatus(int status);

    int turnOnTask(ConsistencyTaskInstance taskInstance);

}
