package com.xonlab.eduorder.service.impl;

import com.xonlab.commonutils.ordervo.CourseWebVoOrder;
import com.xonlab.commonutils.ordervo.UcenterMemberOrder;
import com.xonlab.eduorder.client.EduClient;
import com.xonlab.eduorder.client.UcenterClient;
import com.xonlab.eduorder.entity.Order;
import com.xonlab.eduorder.mapper.OrderMapper;
import com.xonlab.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xonlab.eduorder.utils.OrderNoUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author gao
 * @since 2020-05-04
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrders(String courseId, String memberId) {
        //获取用户信息
        UcenterMemberOrder userInfo = ucenterClient.getUserInfo(memberId);
        //获取课程信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);

        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfo.getMobile());
        order.setNickname(userInfo.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        //返回订单号
        return order.getOrderNo();
    }
}
