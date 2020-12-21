package com.lwg.hrms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {

        return "欢迎进入登录页面！";
    }

    @GetMapping("/employee/basic/hello")
    public String hello2() {
        return "/emp/basic/hello";
    }

    @GetMapping("/employee/advanced/hello")
    public String hello3() {
        return "/emp/adv/hello";
    }

}