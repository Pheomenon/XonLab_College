package com.xonlab.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import com.xonlab.vod.utils.InitVodClient;

import java.util.List;

/**
 * @Author:Gao
 * @Date:2020-04-27 09:50
 */
public class TestVod {
    public static void main(String[] args) throws ClientException {
        //上传视频
        String title = "XonLabCut";
        String fileName = "C:/Users/Gao/Desktop/XonLab.mp4";
        UploadVideoRequest request = new UploadVideoRequest("LTAI4GFBgPprPqBTC7gc5NTE", "yNrZsSpHNqgw5Qa7iiW1uAa6HGy5iH", title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    public static void getPlayAuth() throws ClientException {
        //根据视频id获得播放凭证
        DefaultAcsClient client = InitVodClient.initVodClient("LTAI4GFBgPprPqBTC7gc5NTE", "yNrZsSpHNqgw5Qa7iiW1uAa6HGy5iH");
        //创建获取视频凭证的request和response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        //向request设置视频id
        request.setVideoId("63d7aa8323a74775994073e543ed5ffa");
        //调用初始化对象的方法得到凭证
        response = client.getAcsResponse(request);
        System.out.println("play author:" + response.getPlayAuth());
    }
    //根据视频ID获取视频播放地址
    public static void getPlayUrl() throws ClientException {
        //创建初始化对象
        DefaultAcsClient client = InitVodClient.initVodClient("LTAI4GFBgPprPqBTC7gc5NTE", "yNrZsSpHNqgw5Qa7iiW1uAa6HGy5iH");
        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        //向request对象设置视频id
        request.setVideoId("63d7aa8323a74775994073e543ed5ffa");
        //调用初始化对象里面的方法，传递request，获取数据
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        for(GetPlayInfoResponse.PlayInfo playInfo : playInfoList){
            System.out.println("PlayInfo.PlayURL = "+playInfo.getPlayURL()+"\n");
        }
        System.out.println("VideoBase.Title = "+response.getVideoBase().getTitle());
    }
}
