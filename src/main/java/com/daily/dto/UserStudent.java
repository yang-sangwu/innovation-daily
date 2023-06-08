package com.daily.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author a1002
 */
@Data
public class UserStudent {
    private String code;

    private String username;

    private LocalDateTime signTime;

    //签退时间
    private LocalDateTime outTime;

    //学习内容
    private String learnContent;

    //实际学习内容
    private String realLearnContent;

    //学习时长
    private Long learnTime;

}
