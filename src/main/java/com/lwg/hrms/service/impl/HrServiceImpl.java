package com.lwg.hrms.service.impl;

import com.lwg.hrms.mapper.HrMapper;
import com.lwg.hrms.model.Hr;
import com.lwg.hrms.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author lwg
 * @Date 2020/12/8 13:56
 * @Version 1.0
 */
@Service
public class HrServiceImpl implements HrService , UserDetailsService {

    @Autowired
    private HrMapper hrMapper;

    @Override
    public Hr loadUserByUsername(String username) {
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr==null){
            throw  new UsernameNotFoundException("用户名没有找到");
        }
        //登录成功后 给用户设置角色
        hr.setRoles(hrMapper.getRoleByHrId(hr.getId()));
        return hr;
    }
}
