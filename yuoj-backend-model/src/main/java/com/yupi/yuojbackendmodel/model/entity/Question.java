package com.yupi.yuojbackendmodel.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.yupi.yuojbackendmodel.model.dto.question.LanguageConfig;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目
 * @TableName question
 */
@TableName(value ="question")
@Data
public class Question implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 题目难度 (1-简单;2-中等;3-困难)
     */
    private Integer difficulty;

    /**
     * 标签列表（json 数组）
     */
    private String tags;

    /**
     * 题目答案
     */
    private String answer;

//    /**
//     * 方法名称
//     */
//    private String methodName;
//
//    /**
//     * 方法参数类型
//     */
//    private String types;
//
//    /**
//     * 返回值类型
//     */
//    private String returnType;

    /**
     * 默认代码
     */
    private String defaultCode;

    /**
     * 主类
     */
    private String mainClass;

    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

    /**
     * 判题用例（json 数组）
     */
    private String judgeCase;

    /**
     * 判题配置（json 对象）
     */
    private String judgeConfig;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}