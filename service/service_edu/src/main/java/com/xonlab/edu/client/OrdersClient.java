package com.xonlab.edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author:Gao
 * @Date:2020-05-06 14:27
 */
@Component
@FeignClient(name = "service-order",fallback = OrderDegrade.class)
public interface OrdersClient {
    //根据课程id和用户id查询订单表汇总的订单状态
    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
    public Boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
