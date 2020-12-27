package com.lwg.hrms.service;

import com.lwg.hrms.model.Menu;

import java.util.List;

/**
 * @Author lwg
 * @Date 2020/12/15 13:46
 * @Version 1.0
 */
public interface MenuService {
    List<Menu> getMenusByHrId();

    List<Menu> getAllMenusWithRole();

    List<Menu> getAllMenus();

    List<Integer> getMidsByRid(Integer rid);

    boolean updateMenuRole(Integer rid, Integer[] mids);
}
