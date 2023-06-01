package com.daily.dto;

import lombok.Data;

/**
 * @author a1002
 */
@Data
public class SaveUserDto {
    private String code;

    //学生姓名
    private String username;

    //学生密码
    private String password;

    //班级
    private String stuClass;

    //学习方向
    private String learnDirection;
}
