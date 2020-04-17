package com.xonlab.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author:Gao
 * @Date:2020-04-16 19:17
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
