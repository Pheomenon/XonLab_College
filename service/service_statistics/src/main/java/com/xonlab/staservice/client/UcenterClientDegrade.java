package com.xonlab.staservice.client;

import com.xonlab.commonutils.R;
import org.springframework.stereotype.Component;

/**
 * @Author:Gao
 * @Date:2020-05-06 15:42
 */
@Component
public class UcenterClientDegrade implements UcenterClient{

    @Override
    public R countRegister(String day) {
        return R.error();
    }
}
