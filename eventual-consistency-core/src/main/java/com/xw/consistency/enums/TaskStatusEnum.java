package com.xw.consistency.enums;

public enum TaskStatusEnum {

    INIT(0),
    START(1);

    private final int code;

    TaskStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
