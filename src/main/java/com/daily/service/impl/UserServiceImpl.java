package com.daily.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daily.config.R;
import com.daily.domain.StudentSign;
import com.daily.domain.User;
import com.daily.mapper.StudentSignMapper;
import com.daily.mapper.UserMapper;
import com.daily.service.UserService;
import com.daily.utils.MyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author a1002
 * @Transactional 保证方法内多个数据库操作要么同时成功、要么同时失败
 */
@SuppressWarnings("all")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private StudentSignMapper studentSignMapper;

    @Override
    @Transactional
    public R saveUser(User user) {
        String username = user.getUsername();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user1 = userMapper.selectOne(wrapper);
        if (user1 != null) {
            return R.error("已存在此昵称！");
        }
        String code = String.valueOf(MyUtils.getNumber(6));
        user.setCode(code);
        userMapper.insert(user);
        return R.success("添加成功！");
    }

    @Override
    @Transactional
    public void updateUser(Map<String, Object> map) {
        long id = Long.parseLong((String) map.get("id"));
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        String stuClass = (String) map.get("stuClass");
        String learnDirection = (String) map.get("learnDirection");
        String status = (String) map.get("status");
        String outFlag = (String) map.get("outFlag");
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set(StringUtils.isNotBlank(username), "username", username);
        wrapper.set(StringUtils.isNotBlank(password), "password", password);
        wrapper.set(StringUtils.isNotBlank(stuClass), "stu_class", stuClass);
        wrapper.set(StringUtils.isNotBlank(learnDirection), "learn_direction", learnDirection);
        if (status.length() != 0) {
            int s = Integer.parseInt(status);
            wrapper.set("status", s);
        }
        if (outFlag.length() != 0) {
            int s = Integer.parseInt(outFlag);
            wrapper.set("out_flag", s);
        }
        userMapper.update(null, wrapper);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userMapper.deleteById(id);
    }

    @Override
    public R queryUserPage(Map<String, Object> map) {
        int page = Integer.parseInt((String) map.get("page"));
        int limit = Integer.parseInt((String) map.get("limit"));
        Page<User> pageInfo = new Page<>(page, limit);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        return R.success(userMapper.selectPage(pageInfo, wrapper));
    }

    @Override
    public R queryUserLike(Map<String, Object> map) {
        int page = Integer.parseInt((String) map.get("page"));
        int limit = Integer.parseInt((String) map.get("limit"));
        String thing = (String) map.get("thing");
        String type = (String) map.get("type");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Page<User> pageInfo = new Page<>(page, limit);
        if (type.equals("username")) {
            wrapper.like(StringUtils.isNotBlank(thing), "username", thing);
        } else if (type.equals("stuClass")) {
            wrapper.like(StringUtils.isNotBlank(thing), "stu_class", thing);
        } else if (type.equals("learnDirection")) {
            wrapper.like(StringUtils.isNotBlank(thing), "learn_direction", thing);
        } else {
            QueryWrapper<StudentSign> wq = new QueryWrapper<>();
            Page<StudentSign> pageInfo1 = new Page<>(page, limit);
            long learnTime = Long.parseLong(thing);
            wq.eq("learn_time", learnTime);
            return R.success(studentSignMapper.selectPage(pageInfo1, wq));
        }
        return R.success(userMapper.selectPage(pageInfo, wrapper));
    }
}
