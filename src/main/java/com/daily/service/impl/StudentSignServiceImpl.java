package com.daily.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daily.config.R;
import com.daily.domain.StudentSign;
import com.daily.mapper.StudentSignMapper;
import com.daily.service.StudentSignService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * @author a1002
 * @Transactional 保证方法内多个数据库操作要么同时成功、要么同时失败
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

    @Override
    @Transactional
    public R addSign(Map<String, Object> map) {
        long userId = Long.parseLong((String) map.get("userId"));
        String learnContent = (String) map.get("learnContent");
        LocalDateTime signTime = LocalDateTime.now();
        StudentSign studentSign = new StudentSign(userId, signTime, learnContent);
        return R.success(studentSignMapper.insert(studentSign));
    }

    @Override
    @Transactional
    public R addOut(Map<String, Object> map) {
        long id = Long.parseLong((String) map.get("id"));
        String realLearnContent = (String) map.get("realLearnContent");
        LocalDateTime outTime = LocalDateTime.now();
        StudentSign studentSign = studentSignMapper.selectById(id);
        LocalDateTime signTime = studentSign.getSignTime();
        long minutes = ChronoUnit.MINUTES.between(signTime, outTime);
        studentSign.setOutTime(outTime);
        studentSign.setRealLearnContent(realLearnContent);
        studentSign.setStatus(8);
        studentSign.setLearnTime(minutes);
        int n = studentSignMapper.updateById(studentSign);
        return R.success(n);
    }
}
