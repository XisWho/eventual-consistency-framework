package com.xw.consistency.aspect;

import com.alibaba.fastjson.JSONArray;
import com.xw.consistency.annotation.ConsistencyTask;
import com.xw.consistency.entity.ConsistencyTaskInstance;
import com.xw.consistency.enums.TaskStatusEnum;
import com.xw.consistency.service.TaskService;
import com.xw.consistency.util.ReflectionUtils;
import com.xw.consistency.util.DateTimeUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Aspect
@Component
public class ConsistencyTaskAspect {

    @Autowired
    private TaskService taskService;

    @Around("@annotation(consistencyTask)")
    public Object executeConsistencyTask(ProceedingJoinPoint point, ConsistencyTask consistencyTask) {
        try {
            return point.proceed();
        } catch (Throwable e) {
            // 发生异常时才会执行
            ConsistencyTaskInstance taskInstance = createTaskInstance(consistencyTask, point);
            // 往数据库插入一条任务信息
            taskService.addTask(taskInstance);
            // 任务初始化完成后不对目标方法进行访问，因此返回null
            return null;
        }
    }

    /**
     * 根据注解构造任务实例
     * @param consistencyTask
     * @param point
     * @return
     */
    private ConsistencyTaskInstance createTaskInstance(ConsistencyTask consistencyTask, ProceedingJoinPoint point) {
        String methodFullyQualifiedName = ReflectionUtils.getMethodFullyQualifiedName(point);

        ConsistencyTaskInstance taskInstance = ConsistencyTaskInstance.builder()
                // 这里的信息可能有些重复，但是如果以后要做管理页面，那么可以方便一些查询
                // taskId，他默认用的就是方法全限定名称，所以说，针对一个方法n多次调用，taskId是一样的，所以taskId并不是唯一的id标识
                .taskId(StringUtils.isEmpty(consistencyTask.id()) ? methodFullyQualifiedName : consistencyTask.id())
                .methodName(point.getSignature().getName())
                .parameterTypes(ReflectionUtils.getArgsClassNames((MethodSignature)point.getSignature()))
                .taskParameter(JSONArray.toJSONString(point.getArgs()))
                .methodSignname(methodFullyQualifiedName)
                .executeIntervalSeconds(consistencyTask.executeIntervalSeconds())
                .delaySeconds(consistencyTask.delaySeconds())
                .taskStatus(TaskStatusEnum.INIT.getCode())
                .build();

        // 设置预期的执行时间
        taskInstance.setExecuteTime(getExecuteTime(taskInstance));

        return taskInstance;
    }

    /**
     * 获取任务执行时间
     * @param taskInstance
     * @return
     */
    private String getExecuteTime(ConsistencyTaskInstance taskInstance) {
        return DateTimeUtils.getDateTimeDesc(new Date(System.currentTimeMillis() + taskInstance.getDelaySeconds()*1000));
    }

}
