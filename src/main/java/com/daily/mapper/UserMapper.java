package com.daily.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daily.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author a1002
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    
}
