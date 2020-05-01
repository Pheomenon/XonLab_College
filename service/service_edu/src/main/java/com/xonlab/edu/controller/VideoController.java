package com.xonlab.edu.controller;


import com.xonlab.commonutils.R;
import com.xonlab.edu.client.VodClient;
import com.xonlab.edu.entity.Video;
import com.xonlab.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author gao
 * @since 2020-04-19
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody Video video){
        videoService.save(video);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R deleteVideo(@PathVariable String id){
        //根据小节id获取视频id，然后删除
        Video video = videoService.getById(id);
        String videoSourceId = video.getVideoSourceId();

        if(!StringUtils.isEmpty(videoSourceId)){
            R result = vodClient.removeVideo(videoSourceId);
            if(result.getCode() != 20000){
                throw new RuntimeException();
            }
        }
        videoService.removeById(id);
        return R.ok();
    }

    @PostMapping()
    public R updateVideo(@RequestBody Video video){
        videoService.updateById(video);
        return R.ok();
    }
}