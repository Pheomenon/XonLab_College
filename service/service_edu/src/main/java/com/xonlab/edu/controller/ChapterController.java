package com.xonlab.edu.controller;


import com.xonlab.commonutils.R;
import com.xonlab.edu.entity.Chapter;
import com.xonlab.edu.entity.chapter.ChapterVo;
import com.xonlab.edu.entity.vo.CourseInfoVo;
import com.xonlab.edu.service.ChapterService;
import com.xonlab.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author gao
 * @since 2020-04-19
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    //课程大纲列表,根据课程ID进行查询
    @GetMapping("/getChapterVideo/{courseId}")
    public R getChapter(@PathVariable String courseId){
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",list);
    }

    //添加章节
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody Chapter chapter){
        chapterService.save(chapter);
        return R.ok();
    }

    //根据章节Id查询
    @GetMapping("/getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        Chapter chapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",chapter);
    }

    //修改章节
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody Chapter chapter){
        chapterService.updateById(chapter);
        return R.ok();
    }

    //删除章节
    @DeleteMapping("/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        boolean flag = chapterService.deleteChapter(chapterId);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

