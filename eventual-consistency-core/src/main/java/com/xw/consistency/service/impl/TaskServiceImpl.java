package com.xw.consistency.service.impl;

import com.xw.consistency.dao.TaskDAO;
import com.xw.consistency.entity.ConsistencyTaskInstance;
import com.xw.consistency.enums.TaskStatusEnum;
import com.xw.consistency.service.TaskService;
import com.xw.consistency.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public void addTask(ConsistencyTaskInstance task) {
        taskDAO.insertTask(task);
    }

    @Override
    public List<ConsistencyTaskInstance> queryByStatus(int status) {
        return taskDAO.queryByStatus(status);
    }

    @Override
    public int turnOnTask(ConsistencyTaskInstance taskInstance) {
        taskInstance.setTaskStatus(TaskStatusEnum.START.getCode());
        taskInstance.setExecuteTime(DateTimeUtils.getNowDesc());
        return taskDAO.turnOnTask(taskInstance);
    }

}
