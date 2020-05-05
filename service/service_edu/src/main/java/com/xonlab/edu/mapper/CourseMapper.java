package com.xonlab.edu.mapper;

import com.xonlab.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xonlab.edu.entity.frontvo.CourseWebVo;
import com.xonlab.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author gao
 * @since 2020-04-19
 */
public interface CourseMapper extends BaseMapper<Course> {

    public CoursePublishVo getPublishCourseInfo(String courseId);

    //根据课程id 查询响应信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
