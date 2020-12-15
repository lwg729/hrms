package com.lwg.hrms.controller;

import com.lwg.hrms.model.Menu;
import com.lwg.hrms.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author lwg
 * @Date 2020/12/15 13:45
 * @Version 1.0
 */

@RestController
@RequestMapping("/system/config")
public class SystemConfigController {


    @Autowired
    private MenuServiceImpl menuService;


    @RequestMapping("/menu")
    public List<Menu> getMenusByHrId(){
        return menuService.getMenusByHrId();
    }


}
