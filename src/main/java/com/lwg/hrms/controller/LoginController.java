package com.lwg.hrms.controller;

import com.lwg.hrms.untils.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lwg
 * @Date 2020/12/9 10:54
 * @Version 1.0
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public RespBean login(){
        return RespBean.ok("尚未登录,请登录");
    }
}
