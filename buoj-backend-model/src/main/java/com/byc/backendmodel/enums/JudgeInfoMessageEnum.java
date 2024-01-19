package com.byc.backendmodel.enums;

import org.apache.commons.lang3.ObjectUtils;

/**
 * 判题信息消息枚举
 *
 */
public enum JudgeInfoMessageEnum {

    ACCEPTED("通过", 0),
    WRONG_ANSWER("答案错误", 1),
    COMPILE_ERROR("编译错误", 2),
    RUNTIME_ERROR("运行错误", 3),
    MEMORY_LIMIT_EXCEEDED("超出内存限制", 4),
    TIME_LIMIT_EXCEEDED("超出时间限制", 5),
    OUTPUT_LIMIT_EXCEEDED("输出过长", 6),
    SYSTEM_ERROR("系统错误", 7),
    PRESENTATION_ERROR("展示错误", 8),
    WAITING("等待中", 9),
    DANGEROUS_OPERATION("危险操作", 10);


    private final String text;

    private final Integer value;

    JudgeInfoMessageEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
//    public static List<String> getValues() {
//        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
//    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static JudgeInfoMessageEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (JudgeInfoMessageEnum anEnum : JudgeInfoMessageEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
