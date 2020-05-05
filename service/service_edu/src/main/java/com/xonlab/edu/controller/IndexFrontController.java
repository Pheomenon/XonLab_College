package com.xonlab.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xonlab.commonutils.R;
import com.xonlab.commonutils.ordervo.CourseWebVoOrder;
import com.xonlab.edu.entity.Course;
import com.xonlab.edu.entity.Teacher;
import com.xonlab.edu.entity.frontvo.CourseWebVo;
import com.xonlab.edu.service.CourseService;
import com.xonlab.edu.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:Gao
 * @Date:2020-04-28 19:29
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    //查询前八条热门课程，和前四个老师
    @GetMapping("/index")
    public R index(){
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        List<Course> courseList = courseService.list(courseQueryWrapper);

        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<Teacher> teacherList = teacherService.list(teacherQueryWrapper);

        return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }
}
