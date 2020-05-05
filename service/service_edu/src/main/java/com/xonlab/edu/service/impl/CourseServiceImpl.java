package com.xonlab.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xonlab.edu.entity.Course;
import com.xonlab.edu.entity.CourseDescription;
import com.xonlab.edu.entity.frontvo.CourseFrontVo;
import com.xonlab.edu.entity.frontvo.CourseWebVo;
import com.xonlab.edu.entity.vo.CourseInfoVo;
import com.xonlab.edu.entity.vo.CoursePublishVo;
import com.xonlab.edu.mapper.CourseMapper;
import com.xonlab.edu.service.ChapterService;
import com.xonlab.edu.service.CourseDescriptionService;
import com.xonlab.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xonlab.edu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author gao
 * @since 2020-04-19
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ChapterService chapterService;

    //添加课程信息
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表添加课程基本信息
        //CourseInfoVo对象转换为eduCourse对象
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        int insert = baseMapper.insert(course);

        if(insert == 0){
            //添加失败
            throw new RuntimeException();
        }

        //获取添加之后的课程ID
        String cid = course.getId();

        //向课程简介表添加课程简介
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述Id就是课程Id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //查询课程表
        Course course = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course,courseInfoVo);

        //查询描述表
        CourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        int update = baseMapper.updateById(course);
        if(update == 0){
            throw new RuntimeException();
        }
        //修改描述表
        CourseDescription description = new CourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public void removeCourse(String courseId) {
        //根据课程id删除小节
        videoService.removeVideoByCourseId(courseId);
        //根据课程id删除章节
        chapterService.removeChapterByCourseId(courseId);
        //根据课程id删除描述
        courseDescriptionService.removeById(courseId);
        //根据id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if(result == 0){
            throw new RuntimeException();
        }
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<Course> pageParam, CourseFrontVo courseFrontVo) {
        //根据讲师id查询所讲课程
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        //判断条件值是否为空
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())){
            wrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())){
            wrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())){
            wrapper.orderByDesc("buy_count");
        }
        if(!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())){
            wrapper.orderByDesc("gmt_create");
        }
        if(!StringUtils.isEmpty(courseFrontVo.getPriceSort())){
            wrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageParam,wrapper);

        List<Course> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String,Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }
}
