package com.xonlab.edu.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author:Gao
 * @Date:2020-05-06 14:49
 */
@Component
public class OrderDegrade implements OrdersClient{
    public Boolean isBuyCourse(String courseId, String memberId){
        return false;
    }
}
