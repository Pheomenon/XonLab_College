package com.xonlab.eduorder.controller;


import ch.qos.logback.core.db.dialect.OracleDialect;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xonlab.commonutils.JwtUtils;
import com.xonlab.commonutils.R;
import com.xonlab.eduorder.entity.Order;
import com.xonlab.eduorder.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author gao
 * @since 2020-05-04
 */
@CrossOrigin
@RestController
@RequestMapping("/eduorder/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //生成订单
    @PostMapping("/createOrder/{courseId}")
    public R daveOrder(@PathVariable String courseId, HttpServletRequest request){
        //返回订单号
        String orderNumber = orderService.createOrders(courseId,JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderId",orderNumber);
    }

    //查询订单信息
    @GetMapping("/getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        return R.ok().data("item",order);
    }

    //根据课程id和用户id查询订单表汇总的订单状态
    @GetMapping("/isBuyCourse/{courseId}/{memberId}")
    public Boolean isBuyCourse(@PathVariable String courseId,@PathVariable String memberId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);
        int count = orderService.count(wrapper);
        if(count>0){ // 已经支付
            return true;
        }else {
            return false;
        }
    }


}

