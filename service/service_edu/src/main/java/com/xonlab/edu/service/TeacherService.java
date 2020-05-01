package com.xonlab.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xonlab.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author gao
 * @since 2020-04-11
 */
public interface TeacherService extends IService<Teacher> {

    Map<String, Object> getTeacherFrontList(Page<Teacher> teacherPage);
}
