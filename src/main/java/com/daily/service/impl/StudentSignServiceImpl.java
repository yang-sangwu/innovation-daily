package com.daily.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daily.config.R;
import com.daily.domain.StudentSign;
import com.daily.mapper.StudentSignMapper;
import com.daily.service.StudentSignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author a1002
 */
@Service
public class StudentSignServiceImpl extends ServiceImpl<StudentSignMapper, StudentSign> implements StudentSignService {

    @Resource
    private StudentSignMapper studentSignMapper;

    @Override
    public R querySignByUserId(Map<String, Object> map) {
        int page = Integer.parseInt((String) map.get("page"));
        int limit = Integer.parseInt((String) map.get("limit"));
        long id = Long.parseLong((String) map.get("id"));
        Page<StudentSign> pageInfo = new Page<>(page, limit);
        QueryWrapper<StudentSign> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        return R.success(studentSignMapper.selectPage(pageInfo, wrapper));
    }
}
