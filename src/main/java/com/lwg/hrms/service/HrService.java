package com.lwg.hrms.service;


import com.lwg.hrms.model.Hr;

/**
 * @Author lwg
 * @Date 2020/12/7 23:06
 * @Version 1.0
 */

public interface HrService {

    Hr loadUserByUsername(String username);
}