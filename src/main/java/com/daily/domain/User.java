package com.daily.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author a1002
 */

@SuppressWarnings("all")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //编号
    private String code;

    //学生姓名
    private String username;

    //学生密码
    private String password;

    //班级
    private String stuClass;

    //学习方向
    private String learnDirection;

    //0为普通用户，为管理员
    private Integer status;

    //是否退出小组，0为没有退出，1为已退出
    private Integer outFlag;

    public User(String code, String username, String password, String stuClass, String learnDirection) {
        this.code = code;
        this.username = username;
        this.password = password;
        this.stuClass = stuClass;
        this.learnDirection = learnDirection;
    }
}
