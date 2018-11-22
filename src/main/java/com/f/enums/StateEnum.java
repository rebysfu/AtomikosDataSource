package com.f.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-10-11 下午4:30
 **/
@AllArgsConstructor()
public enum StateEnum {
    NORMAL(1, "正常的"),
    DISABLED(2, "禁用的");
    @Getter
    private Integer code;
    @Getter
    private String desc;

    public static boolean isValidcode(Integer code) {
        for (StateEnum stateEnum : StateEnum.values()) {
            if (stateEnum.code.equals(code)) {
                return true;
            }
        }
        return false;
    }
}
