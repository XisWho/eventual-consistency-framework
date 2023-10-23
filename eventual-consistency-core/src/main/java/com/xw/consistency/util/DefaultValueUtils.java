package com.xw.consistency.util;

import org.springframework.util.StringUtils;

public class DefaultValueUtils {

    public static String getOrDefault(String value, String defaultValue) {
        return StringUtils.isEmpty(value) ? defaultValue : value;
    }

    public static Integer getOrDefault(Integer value, Integer defaultValue) {
        return value==null ? defaultValue : value;
    }

    public static Long getOrDefault(Long value, Long defaultValue) {
        return value==null ? defaultValue : value;
    }

}
