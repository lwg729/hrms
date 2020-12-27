package com.lwg.hrms.controller.system.basic;

import com.lwg.hrms.model.Menu;
import com.lwg.hrms.model.Role;
import com.lwg.hrms.service.impl.MenuServiceImpl;
import com.lwg.hrms.service.impl.RoleServiceImpl;
import com.lwg.hrms.untils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/mids/{rid}")
    public List<Integer> getMenusByRid(@PathVariable Integer rid){
        return  menuService.getMidsByRid(rid);
    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        if (menuService.updateMenuRole(rid,mids)){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role){
        if (roleService.addRole(role)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @DeleteMapping("/role/{id}")
    public RespBean deleteRoleById(@PathVariable Integer id){
        if (roleService.deleteRoleById(id)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
