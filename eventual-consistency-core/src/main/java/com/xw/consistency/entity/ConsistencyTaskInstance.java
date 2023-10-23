package com.xw.consistency.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsistencyTaskInstance {
    /**
     * id
     */
    private Long id;
    /**
     *  用户在注解中自定义的id
     */
    private String taskId;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数的类路径名称
     */
    private String parameterTypes;
    /**
     * 参数的JSON值
     */
    private String taskParameter;
    /**
     * 方法签名：格式：类路径#方法名(参数1的类型,参数2的类型,...参数N的类型)
     */
    private String methodSignname;
    /**
     * 执行间隔
     */
    private Integer executeIntervalSeconds;
    /**
     * 初始延迟时间
     */
    private Integer delaySeconds;
    /**
     * 任务状态
     */
    private Integer taskStatus;
    /**
     * 执行时间
     * @return
     */
    private String executeTime;
}
