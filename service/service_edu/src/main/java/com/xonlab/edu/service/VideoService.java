package com.xonlab.edu.service;

import com.xonlab.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author gao
 * @since 2020-04-19
 */
public interface VideoService extends IService<Video> {

    void removeVideoByCourseId(String courseId);
}
