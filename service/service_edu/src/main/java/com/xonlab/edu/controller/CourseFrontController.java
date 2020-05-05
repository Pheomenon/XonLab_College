package com.xonlab.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xonlab.commonutils.R;
import com.xonlab.commonutils.ordervo.CourseWebVoOrder;
import com.xonlab.edu.entity.Course;
import com.xonlab.edu.entity.Teacher;
import com.xonlab.edu.entity.chapter.ChapterVo;
import com.xonlab.edu.entity.frontvo.CourseFrontVo;
import com.xonlab.edu.entity.frontvo.CourseWebVo;
import com.xonlab.edu.entity.vo.TeacherQuery;
import com.xonlab.edu.service.ChapterService;
import com.xonlab.edu.service.CourseService;
import com.xonlab.edu.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author gao
 * @since 2020-04-11
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @PostMapping("/getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit, @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<Course> coursePage = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(coursePage,courseFrontVo);
        return R.ok().data(map);
    }

    @GetMapping("/getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId){
        //根据课程id查询相应信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList);
    }


    //查询课程信息
    @PostMapping("/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}

