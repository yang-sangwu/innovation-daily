package com.daily.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daily.domain.User;

import java.util.Map;

/**
 * @author a1002
 */
public interface UserService extends IService<User> {
    boolean save(User user);

    void updateUser(Map<String, Object> map);

    void deleteUser(long id);
}
