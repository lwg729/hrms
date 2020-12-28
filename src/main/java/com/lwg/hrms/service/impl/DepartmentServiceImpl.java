package com.lwg.hrms.service.impl;

import com.lwg.hrms.mapper.DepartmentMapper;
import com.lwg.hrms.model.Department;
import com.lwg.hrms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lwg
 * @Date 2020/12/28 14:44
 * @Version 1.0
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }
}
