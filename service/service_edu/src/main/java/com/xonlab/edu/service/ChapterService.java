package com.xonlab.edu.service;

import com.xonlab.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xonlab.edu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author gao
 * @since 2020-04-19
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
