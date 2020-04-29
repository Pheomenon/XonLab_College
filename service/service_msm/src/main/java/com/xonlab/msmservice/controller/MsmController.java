package com.xonlab.msmservice.controller;

import com.xonlab.commonutils.R;
import com.xonlab.msmservice.service.MsmService;
import com.xonlab.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Gao
 * @Date:2020-04-29 14:45
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送短信
    @GetMapping("/send/{phone}")
    public R sendMsm(@PathVariable String phone){
        String code = redisTemplate.opsForValue().get("phone");
        if(!StringUtils.isEmpty(code)){
            return R.ok();
        }

        code = RandomUtil.getSixBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        boolean isSend = msmService.send(param,phone);
        if(isSend) {
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        }
        else {
            return R.error().message("短信发送失败");
        }
    }
}
