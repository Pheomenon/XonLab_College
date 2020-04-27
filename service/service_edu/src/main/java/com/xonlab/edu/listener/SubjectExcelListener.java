package com.xonlab.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xonlab.edu.entity.Subject;
import com.xonlab.edu.entity.excel.SubjectData;
import com.xonlab.edu.service.SubjectService;

/**
 * @Author:Gao
 * @Date:2020-04-19 10:04
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public SubjectService subjectService;

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public SubjectExcelListener() {}

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        Subject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if(existOneSubject == null){
            //没有相同的一级分类，进行添加
            existOneSubject = new Subject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }
        String pid = existOneSubject.getId();
        Subject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if(existTwoSubject == null){
            existTwoSubject = new Subject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }

    //判断一级分类不能重复
    private Subject existOneSubject(SubjectService subjectService,String name){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",0);
        Subject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类不能重复
    private Subject existTwoSubject(SubjectService subjectService,String name,String pid){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        Subject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
