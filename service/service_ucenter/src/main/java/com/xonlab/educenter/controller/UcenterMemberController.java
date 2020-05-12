package com.xonlab.educenter.controller;


import com.xonlab.commonutils.JwtUtils;
import com.xonlab.commonutils.R;
import com.xonlab.commonutils.ordervo.UcenterMemberOrder;
import com.xonlab.educenter.entity.UcenterMember;
import com.xonlab.educenter.entity.vo.RegisterVo;
import com.xonlab.educenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author gao
 * @since 2020-04-29
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("/login")
    public R loginUser(@RequestBody UcenterMember member){
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }

    @PostMapping("/register")
    public R registerUser(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    @GetMapping("/getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }
    //根据用户id获取信息
    @PostMapping("/getUserInfo/{id}")
    public UcenterMemberOrder getUserInfo(@PathVariable String id){
        UcenterMember member = memberService.getById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    //查询某一天的注册人数
    @GetMapping("/countRegister/{day}")
    public R countRegister(@PathVariable String day){
        Integer count = memberService.countRegisterDay(day);
        return R.ok().data("countRegister",count);
    }
}

