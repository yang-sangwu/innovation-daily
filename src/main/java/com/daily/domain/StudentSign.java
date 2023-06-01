package com.daily.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author a1002
 */
@SuppressWarnings("all")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StudentSign implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //用户id
    private Long userId;

    //签到时间
    private LocalDateTime signTime;

    //签退时间
    private LocalDateTime outTime;

    //学习内容
    private String learnContent;

    //实际学习内容
    private String realLearnContent;

    //学习时长
    private String learnTime;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    //创建人
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    //修改人
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    public StudentSign(Long userId, LocalDateTime signTime, String learnContent) {
        this.userId = userId;
        this.signTime = signTime;
        this.learnContent = learnContent;
    }

    public StudentSign(Long userId, LocalDateTime signTime, LocalDateTime outTime, String learnContent, String realLearnContent, String learnTime) {
        this.userId = userId;
        this.signTime = signTime;
        this.outTime = outTime;
        this.learnContent = learnContent;
        this.realLearnContent = realLearnContent;
        this.learnTime = learnTime;
    }
}
