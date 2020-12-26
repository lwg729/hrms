package com.lwg.hrms.controller.system.basic;

import com.lwg.hrms.model.Menu;
import com.lwg.hrms.model.Role;
import com.lwg.hrms.service.impl.MenuServiceImpl;
import com.lwg.hrms.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author lwg
 * @Date 2020/12/26 23:42
 * @Version 1.0
 */

@RestController
@RequestMapping("/system/basic/perSet")
public class PermissionSetController {

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private MenuServiceImpl menuService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }
}
