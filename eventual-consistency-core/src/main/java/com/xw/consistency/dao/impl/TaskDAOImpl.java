package com.xw.consistency.dao.impl;

import com.xw.consistency.dao.TaskDAO;
import com.xw.consistency.entity.ConsistencyTaskInstance;
import com.xw.consistency.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public void insertTask(ConsistencyTaskInstance task) {
        taskMapper.insertTask(task);
    }

    @Override
    public List<ConsistencyTaskInstance> queryByStatus(int status) {
        return taskMapper.queryByStatus(status);
    }

    @Override
    public int turnOnTask(ConsistencyTaskInstance taskInstance) {
        return taskMapper.turnOnTask(taskInstance);
    }

}
