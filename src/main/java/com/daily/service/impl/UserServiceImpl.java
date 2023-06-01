package com.daily.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daily.domain.User;
import com.daily.mapper.UserMapper;
import com.daily.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author a1002
 */
@SuppressWarnings("all")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public boolean save(User user) {
        userMapper.insert(user);
        return true;
    }

    @Override
    @Transactional
    public void updateUser(Map<String, Object> map) {
        long id = Long.parseLong((String) map.get("id"));
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        String stuClass = (String) map.get("stuClass");
        String learnDirection = (String) map.get("learnDirection");
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set(StringUtils.isNotBlank(username), "username", username);
        wrapper.set(StringUtils.isNotBlank(password), "password", password);
        wrapper.set(StringUtils.isNotBlank(stuClass), "stu_class", stuClass);
        wrapper.set(StringUtils.isNotBlank(learnDirection), "learn_direction", learnDirection);
        userMapper.update(null, wrapper);
        // userMapper.updateById(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userMapper.deleteById(id);
    }
}
