package com.xonlab.edu.service;

import com.xonlab.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xonlab.edu.entity.vo.CourseInfoVo;
import com.xonlab.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author gao
 * @since 2020-04-19
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);
}
