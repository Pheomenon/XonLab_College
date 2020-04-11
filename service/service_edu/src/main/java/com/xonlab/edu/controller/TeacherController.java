package com.xonlab.edu.controller;


import com.xonlab.edu.entity.Teacher;
import com.xonlab.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Teacher> findAllTeacher(){
        List<Teacher> list = teacherService.list(null);
        return list;
    }

    @DeleteMapping("/{id}")
    public boolean removeTeacher(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        return flag;
    }


}

