package com.xonlab.edu.controller;

import com.xonlab.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:Gao
 * @Date:2020-04-13 19:47
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/user")
public class LoginController {
    //login
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","C:\\Users\\Gao\\Desktop\\20150810224146_zJEhY.jpeg");
    }
}
