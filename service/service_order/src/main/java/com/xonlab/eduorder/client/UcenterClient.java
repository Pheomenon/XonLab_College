package com.xonlab.eduorder.client;

import com.xonlab.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author:Gao
 * @Date:2020-05-04 19:45
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //根据用户id获取信息
    @PostMapping("/educenter/member/getUserInfo/{id}")
    public UcenterMemberOrder getUserInfo(@PathVariable("id") String id);
}
