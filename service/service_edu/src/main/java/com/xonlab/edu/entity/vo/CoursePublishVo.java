package com.xonlab.edu.entity.vo;

import lombok.Data;

/**
 * @Author:Gao
 * @Date:2020-04-26 16:07
 */
@Data
public class CoursePublishVo {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
