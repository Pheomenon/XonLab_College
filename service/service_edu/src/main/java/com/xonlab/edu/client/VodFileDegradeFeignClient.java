package com.xonlab.edu.client;

import com.xonlab.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Gao
 * @Date:2020-04-27 22:39
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{

    @Override
    public R removeVideo(String id) {
        return R.error().message("删除视频出错");
    }

    @Override
    public R deleteBatch(List<String> videoList) {
        return R.error().message("批量删除出错");
    }
}
