package com.xw.consistency.enable;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 包含Spring和MyBatis的扫描配置
 */
@Configuration
@ComponentScan(value = {"com.xw.consistency"})
@MapperScan(basePackages = {"com.xw.consistency.mapper"})
public class ComponentScanConfig {
}
