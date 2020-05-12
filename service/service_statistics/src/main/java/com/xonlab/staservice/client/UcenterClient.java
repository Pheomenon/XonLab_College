package com.xonlab.staservice.client;

import com.xonlab.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author:Gao
 * @Date:2020-05-06 15:37
 */
@Component
@FeignClient(value = "service-ucenter",fallback = UcenterClientDegrade.class)
public interface UcenterClient {
    @GetMapping("/educenter/member/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);
}
