package com.lwg.hrms.service.impl;

import com.lwg.hrms.mapper.MenuMapper;
import com.lwg.hrms.model.Hr;
import com.lwg.hrms.model.Menu;
import com.lwg.hrms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lwg
 * @Date 2020/12/15 13:47
 * @Version 1.0
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getMenusByHrId() {
        return menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());

    }

    @Override
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }
}
