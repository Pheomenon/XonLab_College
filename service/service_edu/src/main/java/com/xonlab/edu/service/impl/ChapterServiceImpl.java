package com.xonlab.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xonlab.edu.entity.Chapter;
import com.xonlab.edu.entity.Video;
import com.xonlab.edu.entity.chapter.ChapterVo;
import com.xonlab.edu.entity.chapter.VideoVo;
import com.xonlab.edu.mapper.ChapterMapper;
import com.xonlab.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xonlab.edu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author gao
 * @since 2020-04-19
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;//注入小节Service

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //根据课程Id查询课程中的所有章节
        QueryWrapper<Chapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<Chapter> chapterList = baseMapper.selectList(wrapperChapter);

        //根据课程Id查询课程里面的所有小节
        QueryWrapper<Video> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<Video> videoList = videoService.list(wrapperVideo);

        //遍历查询章节list集合进行封装
        List<ChapterVo> finalList = new ArrayList<>();
        for (int i = 0; i < chapterList.size(); i++) {
            //每个章节
            Chapter chapter = chapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            finalList.add(chapterVo);

            //创建集合，用于封装章节的小节
            List<VideoVo> videoList1 = new ArrayList<>();

            //遍历小节list集合，进行封装
            for (int m = 0; m < videoList.size(); m++) {
                //得到每个小节
                Video video = videoList.get(m);
                if(video.getChapterId().equals(chapter.getId())){
                    //进行封装
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoList1.add(videoVo);
                }
            }

            //把封装之后小节List集合放到章节对象中
            chapterVo.setChildren(videoList1);
        }
        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        if(count > 0){ //查询出小节不进行删除
            throw new RuntimeException();
        }else {
            int result = baseMapper.deleteById(chapterId);
            return result>0;
        }
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
