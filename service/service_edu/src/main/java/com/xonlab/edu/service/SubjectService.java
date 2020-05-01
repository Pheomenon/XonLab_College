package com.xonlab.edu.service;

import com.xonlab.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xonlab.edu.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author gao
 * @since 2020-04-19
 */
public interface SubjectService extends IService<Subject> {

    //添加课程分类
    void saveSubject(MultipartFile file,SubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}
