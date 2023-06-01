package com.daily.controller;

import com.daily.config.R;
import com.daily.domain.User;
import com.daily.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author a1002
 */

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 根据id查询小组成员
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable long id) {
        User user = userService.getById(id);
        if (user != null) {
            return R.success(user);
        } else {
            return R.error("不存在该用户！");
        }
    }

    /**
     * 小组成员的添加
     */
    @PostMapping("/save")
    public R save(User user) {
        return R.success(userService.save(user));
    }

    
}
