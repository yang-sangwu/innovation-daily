package com.daily.controller;

import com.daily.config.R;
import com.daily.domain.User;
import com.daily.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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

    /**
     * 小组成员的删除
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable long id) {
        User user = userService.getById(id);
        if (user != null) {
            userService.deleteUser(id);
            return R.success("success!");
        } else {
            return R.error("不存在该用户！");
        }
    }


    /**
     * 小组成员的修改
     */
    @PutMapping("/update")
    public R update(@RequestParam Map<String, Object> map) {
        userService.updateUser(map);
        return R.success("success!");
    }

}
