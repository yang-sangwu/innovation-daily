package com.daily.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daily.config.R;
import com.daily.domain.StudentSign;

import java.util.Map;

/**
 * @author a1002
 */
public interface StudentSignService extends IService<StudentSign> {
    R querySignByUserId(Map<String, Object> map);

    R addSign(Map<String, Object> map);

    R addOut(Map<String, Object> map);
}
