package com.xw.consistency.mapper;

import com.xw.consistency.entity.ConsistencyTaskInstance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {

    void insertTask(ConsistencyTaskInstance task);

    List<ConsistencyTaskInstance> queryByStatus(int status);

    int turnOnTask(ConsistencyTaskInstance taskInstance);

}
