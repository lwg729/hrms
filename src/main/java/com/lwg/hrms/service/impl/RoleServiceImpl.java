package com.lwg.hrms.service.impl;

import com.lwg.hrms.mapper.RoleMapper;
import com.lwg.hrms.model.Role;
import com.lwg.hrms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lwg
 * @Date 2020/12/26 23:45
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }
}
