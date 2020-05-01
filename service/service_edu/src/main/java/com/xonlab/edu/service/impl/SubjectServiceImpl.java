package com.xonlab.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xonlab.edu.entity.Subject;
import com.xonlab.edu.entity.excel.SubjectData;
import com.xonlab.edu.entity.subject.OneSubject;
import com.xonlab.edu.entity.subject.TwoSubject;
import com.xonlab.edu.listener.SubjectExcelListener;
import com.xonlab.edu.mapper.SubjectMapper;
import com.xonlab.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author gao
 * @since 2020-04-19
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void saveSubject(MultipartFile file,SubjectService subjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //课程分类列表（树形）
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询所有一级分类
        QueryWrapper<Subject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<Subject> oneSubjectsList = baseMapper.selectList(wrapperOne);

        //查询所有二级分类
        QueryWrapper<Subject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<Subject> twoSubjectsList = baseMapper.selectList(wrapperTwo);

        //创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值
        //封装到要求的list集合里面List<OneSubject> finalSubjectList
        for (int i = 0; i < oneSubjectsList.size(); i++) {
            Subject subject = oneSubjectsList.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(subject,oneSubject);
            //多个OneSubject放到finalSubjectList中去
            finalSubjectList.add(oneSubject);

            //在一级分类循环遍历查询所有二级分类
            //创建list集合封装每个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectsList = new ArrayList<>();
            //遍历二级分类list集合
            for (int m = 0; m < twoSubjectsList.size(); m++) {
                //获取所有二级分类
                Subject tSubject = twoSubjectsList.get(m);
                if(tSubject.getParentId().equals(subject.getId())){
                    //把tSubject值复制到TwoSubject里面，放到twoFinalSubjectList里面
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectsList.add(twoSubject);
                }
            }
            //把一级下面所有二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectsList);
        }

        //封装二级分类

        return finalSubjectList;
    }
}
