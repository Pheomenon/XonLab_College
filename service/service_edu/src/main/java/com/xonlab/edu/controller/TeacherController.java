package com.xonlab.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xonlab.commonutils.R;
import com.xonlab.edu.entity.Teacher;
import com.xonlab.edu.entity.vo.TeacherQuery;
import com.xonlab.edu.service.TeacherService;
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
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping(value = "/findAll")
    public R findAllTeacher(){
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    @DeleteMapping("/{id}")
    public R removeTeacher(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if(flag){
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    /**
     * 分页查询讲师
     * @param current
     * @param limit
     * @return
     */
    @GetMapping(value = "/pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        Page<Teacher> teacherPage = new Page<>(current,limit);
        teacherService.page(teacherPage,null);
        long total = teacherPage.getTotal();
        List<Teacher> records = teacherPage.getRecords(); //数据集合
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);
    }

    @PostMapping("/teacherPageCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        //创建Page对象
        Page<Teacher> teacherPage = new Page<>(current,limit);

        //构建条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断传进来的值是不是空，不为空的时候拼接条件
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.gt("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.le("gmt_create",end);
        }
        //分页查询
        teacherService.page(teacherPage,wrapper);
        long total = teacherPage.getTotal();
        List<Teacher> records = teacherPage.getRecords(); //数据集合
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);
    }

}

