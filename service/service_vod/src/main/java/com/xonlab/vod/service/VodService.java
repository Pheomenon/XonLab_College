package com.xonlab.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author:Gao
 * @Date:2020-04-27 10:49
 */
public interface VodService {
    String uploadVideo(MultipartFile file);

    void removeAllAliVod(List videoList);
}
