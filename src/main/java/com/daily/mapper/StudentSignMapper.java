package com.daily.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daily.domain.StudentSign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author a1002
 */
@Mapper
public interface StudentSignMapper extends BaseMapper<StudentSign> {
    @Select("select sum(learn_time) from student_sign where user_id =#{id}")
    Long querySumLearnTime(long id);
}
