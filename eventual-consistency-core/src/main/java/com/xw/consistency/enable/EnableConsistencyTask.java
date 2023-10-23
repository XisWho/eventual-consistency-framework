package com.xw.consistency.enable;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用一致性框架的注解
 *
 * @author zhonghuashishan
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import({ConsistencyTaskImportSelector.class})
public @interface EnableConsistencyTask {
}
