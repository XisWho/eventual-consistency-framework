package com.xw.consistency.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReflectionUtils {

    /**
     * 初始化基础数据类型的MAP
     */
    private static final HashMap<String, Class> PRIMITIVE_TYPE_MAP = new HashMap<String, Class>() {
        {
            put("int", int.class);
            put("double", double.class);
            put("float", float.class);
            put("long", long.class);
            put("short", short.class);
            put("boolean", boolean.class);
            put("char", char.class);
        }
    };

    public static String getMethodFullyQualifiedName(ProceedingJoinPoint point) {
        StringBuilder methodFullyQualifiedName = new StringBuilder();
        methodFullyQualifiedName.append(point.getTarget().getClass().getName())
                .append("#")
                .append(point.getSignature().getName());

        methodFullyQualifiedName.append("(");

        // 通过point.getArgs()方式的参数类型中，会把原始类型替换成包装类型，即int会被替换成Integer
        // 所以通过point.getSignature()来修复
//        methodFullyQualifiedName.append(
//                Arrays.stream(point.getArgs())
//                        .map(Object::getClass)
//                        .map(Class::getName)
//                        .reduce((className1, className2) -> className1 + "," + className2)
//                        .orElse("")
//        );
        methodFullyQualifiedName.append(getArgsClassNames((MethodSignature)point.getSignature()));

        methodFullyQualifiedName.append(")");

        return methodFullyQualifiedName.toString();
    }

    public static String getArgsClassNames(MethodSignature methodSignature) {
        return Arrays.stream(methodSignature.getParameterTypes())
                .map(Class::getName)
                .reduce((init, parameterTypeName) -> init + "," + parameterTypeName)
                .orElse("");
    }

    public static Class<?>[] buildTypeClassArray(String[] parameterTypes) {
        if (parameterTypes==null || parameterTypes.length==0) {
            return new Class<?>[]{};
        }

        Class<?>[] parameterTypeClassArray = new Class<?>[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            try {
                Class clazz = PRIMITIVE_TYPE_MAP.get(parameterTypes[i]);
                if (clazz != null) {
                    parameterTypeClassArray[i] = clazz;
                } else {
                    parameterTypeClassArray[i] = Class.forName(parameterTypes[i]);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return parameterTypeClassArray;
    }

    public static Object[] buildArgArray(String parameterText, Class<?>[] parameterClasses) {
        JSONArray paramJsonArray = JSONArray.parseArray(parameterText);
        Object[] args = new Object[paramJsonArray.size()];

        for (int i=0; i<paramJsonArray.size(); i++) {
            args[i] = JSONObject.parseObject(paramJsonArray.getString(i), parameterClasses[i]);
        }

        return args;
    }

}
