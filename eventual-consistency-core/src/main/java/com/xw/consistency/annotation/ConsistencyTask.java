package com.xw.consistency.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface ConsistencyTask {

    /**
     * 任务id
     * @return
     */
    String id() default "";
    /**
     * 执行间隔
     * @return
     */
    int executeIntervalSeconds() default 0;
    /**
     * 延迟时间
     * @return
     */
    int delaySeconds() default 0;

}
