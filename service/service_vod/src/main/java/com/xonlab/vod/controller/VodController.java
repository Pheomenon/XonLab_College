package com.xonlab.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.xonlab.commonutils.R;
import com.xonlab.vod.service.VodService;
import com.xonlab.vod.utils.ConstantVod;
import com.xonlab.vod.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author:Gao
 * @Date:2020-04-27 10:48
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("/uploadVideo")
    public R uploadVideo(MultipartFile file){
        //返回上传视频的id
        String videoId = vodService.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }

    @DeleteMapping("/removeVideo/{id}")
    public R removeVideo(@PathVariable String id){
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVod.ACCESS_KEY_ID, ConstantVod.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    //批量删除阿里视频
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoList") List<String> videoList){
        vodService.removeAllAliVod(videoList);

        return R.ok();
    }
}
