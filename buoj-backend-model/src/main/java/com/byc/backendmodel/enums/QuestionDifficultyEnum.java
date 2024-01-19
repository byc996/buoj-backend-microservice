package com.byc.backendmodel.enums;

public enum QuestionDifficultyEnum {

    EASY("简单", 0),
    MEDIUM("中等", 1),
    HARD("困难", 2);

    private final String text;

    private final Integer value;

    QuestionDifficultyEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }
    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
