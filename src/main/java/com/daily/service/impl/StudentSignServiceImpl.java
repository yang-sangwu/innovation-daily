package com.daily.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daily.domain.StudentSign;
import com.daily.mapper.StudentSignMapper;
import com.daily.service.StudentSignService;
import org.springframework.stereotype.Service;

/**
 * @author a1002
 */
@Service
public class StudentSignServiceImpl extends ServiceImpl<StudentSignMapper, StudentSign> implements StudentSignService {
}
