package com.lwg.hrms.service.impl;

import com.lwg.hrms.mapper.JobLevelMapper;
import com.lwg.hrms.model.JobLevel;
import com.lwg.hrms.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author lwg
 * @Date 2020/12/23 16:23
 * @Version 1.0
 */
@Service
public class JobLevelServiceImpl implements JobLevelService {

    @Autowired
    private JobLevelMapper jobLevelMapper;

    @Override
    public List<JobLevel> getAllJobLevels() {
        return jobLevelMapper.getAllJobLevels();
    }

    @Override
    public int addJobLevel(JobLevel jobLevel) {
        jobLevel.setcreateDate(new Date());
        jobLevel.setEnabled(true);
        return jobLevelMapper.insertSelective(jobLevel);
    }

    @Override
    public int updateJobLevel(JobLevel jobLevel) {
        jobLevel.setcreateDate(new Date());
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    @Override
    public int deleteJobLevelById(Integer id) {
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteJobLevelByIds(Integer[] ids) {
        return jobLevelMapper.deleteJobLevelByIds(ids);
    }
}
