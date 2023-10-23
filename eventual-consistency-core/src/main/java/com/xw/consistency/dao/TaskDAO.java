package com.xw.consistency.dao;

import com.xw.consistency.entity.ConsistencyTaskInstance;

import java.util.List;

public interface TaskDAO {

    void insertTask(ConsistencyTaskInstance task);

    List<ConsistencyTaskInstance> queryByStatus(int status);

    int turnOnTask(ConsistencyTaskInstance taskInstance);

}
