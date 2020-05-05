package com.xonlab.eduorder.controller;


import com.xonlab.commonutils.R;
import com.xonlab.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author gao
 * @since 2020-05-04
 */
@RestController
@RequestMapping("/eduorder/paylog")
@CrossOrigin
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    //根据订单号生成二维码
    @GetMapping("/createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo){
        //返回二维码地址和其他信息
        Map map = payLogService.createNative(orderNo);
        return R.ok().data(map);
    }

    //查询订单状态
    @GetMapping("/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo){
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        System.out.println(map);
        if(map == null){
            return R.error().message("支付出错");
        }
        if(map.get("trade_state").equals("SUCCESS")){

            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }
}