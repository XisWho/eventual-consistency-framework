package com.xw.consistency.schedule.local;

import com.alibaba.fastjson.JSONArray;
import com.xw.consistency.entity.ConsistencyTaskInstance;
import com.xw.consistency.enums.TaskStatusEnum;
import com.xw.consistency.service.TaskService;
import com.xw.consistency.util.DateTimeUtils;
import com.xw.consistency.util.ReflectionUtils;
import com.xw.consistency.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LocalExecutor {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CompletionService<ConsistencyTaskInstance> consistencyTaskPool;

    public void execute() throws Exception {
        List<ConsistencyTaskInstance> taskInstances = taskService.queryByStatus(TaskStatusEnum.INIT.getCode());
        if (CollectionUtils.isEmpty(taskInstances)) {
            return;
        }

        taskInstances = taskInstances.stream()
                .filter(taskInstance -> {
                    try {
                        return DateTimeUtils.getDateTime(taskInstance.getExecuteTime()).before(new Date());
                    } catch (ParseException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());

        CountDownLatch latch = new CountDownLatch(taskInstances.size());
        taskInstances.stream().forEach(task -> {
            consistencyTaskPool.submit(() -> {
                try {
                    doExecute(task);
                } catch (Exception e) {

                } finally {
                    latch.countDown();
                }
                return task;
            });
        });
        latch.await();
    }

    @Transactional(rollbackFor = Exception.class)
    public void doExecute(ConsistencyTaskInstance taskInstance) {
        try {
            // 启动任务，更新任务状态和执行时间
            taskService.turnOnTask(taskInstance);

            // 获取方法签名，格式：类路径#方法名(参数1的类型,参数2的类型,...参数N的类型)
            String methodSignName = taskInstance.getMethodSignname();
            String methodClass = methodSignName.split("#")[0];
            Class<?> clazz = Class.forName(methodClass);
            if (ObjectUtils.isEmpty(clazz)) {

            }

            Object bean = SpringUtils.getBean(clazz);
            if (ObjectUtils.isEmpty(clazz)) {

            }

            String methodName = taskInstance.getMethodName();
            String[] parameterTypes = taskInstance.getParameterTypes().split(",");
            Class<?>[] parameterClasses = ReflectionUtils.buildTypeClassArray(parameterTypes);
            Method targetMethod = clazz.getMethod(methodName, parameterClasses);
            if (ObjectUtils.isEmpty(targetMethod)) {
                return;
            }

            Object[] args = ReflectionUtils.buildArgArray(taskInstance.getTaskParameter(), parameterClasses);

            targetMethod.invoke(bean, args);
        } catch (Exception e) {

        }
    }

}
