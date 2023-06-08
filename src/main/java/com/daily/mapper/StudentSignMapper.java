package com.daily.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daily.domain.StudentSign;
import com.daily.dto.UserStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author a1002
 */
@Mapper
public interface StudentSignMapper extends BaseMapper<StudentSign> {
    @Select("select sum(learn_time) from student_sign where user_id =#{id}")
    Long querySumLearnTime(long id);

    @Select("select code,username,sign_time,out_time,learn_content,real_learn_content,learn_time from user right join student_sign on student_sign.user_id=user.id")
    List<UserStudent> queryUserStudentPage();
}
