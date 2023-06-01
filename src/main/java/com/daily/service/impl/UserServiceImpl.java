package com.daily.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daily.domain.User;
import com.daily.mapper.UserMapper;
import com.daily.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author a1002
 */
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
    public void updateUser(User user) {
        userMapper.updateById(user);
    }
}
