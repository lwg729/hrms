package com.lwg.hrms.service;

import com.lwg.hrms.model.JobLevel;

import java.util.List;

/**
 * @Author lwg
 * @Date 2020/12/23 16:23
 * @Version 1.0
 */
public interface JobLevelService {
    List<JobLevel> getAllJobLevels();

    int addJobLevel(JobLevel jobLevel);

    int updateJobLevel(JobLevel jobLevel);

    int deleteJobLevelById(Integer id);

    int deleteJobLevelByIds(Integer[] ids);
}
