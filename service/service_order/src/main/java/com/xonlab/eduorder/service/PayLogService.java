package com.xonlab.eduorder.service;

import com.xonlab.eduorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author gao
 * @since 2020-05-04
 */
public interface PayLogService extends IService<PayLog> {

    Map createNative(String orderNo);

    void updateOrderStatus(Map<String, String> map);

    Map<String, String> queryPayStatus(String orderNo);
}
