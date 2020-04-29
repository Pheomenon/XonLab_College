package com.xonlab.msmservice.service;

import java.util.Map;

/**
 * @Author:Gao
 * @Date:2020-04-29 14:45
 */
public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
