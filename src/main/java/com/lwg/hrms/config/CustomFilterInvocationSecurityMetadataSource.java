package com.lwg.hrms.config;

import com.lwg.hrms.mapper.MenuMapper;
import com.lwg.hrms.model.Menu;
import com.lwg.hrms.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @Author lwg
 * @Date 2020/12/17 15:59
 * @Version 1.0
 */
//根据用户的请求地址获取对应的角色
//根据前端传回来的http 与后端服务中查询得到的菜单的url进行比较 然后再根据url查询得到所对应的role的name 然后将
//得到的name角色 返回给collection
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuMapper menuMapper;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> menus = menuMapper.getAllMenusWithRole();
        //循环遍历所有的菜单
        for (Menu menu : menus) {
            //前端传回的http与服务端的一致后,可以查询得到对应的role了  一个菜单拥有一个或多个角色
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {

                List<Role> roles = menu.getRoles();

                //需要传入string数组
                String[] str = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    str[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(str);
            }
        }
        //当请求的路径 数据库中不存在时 例如system/config 统一设置为登录后即可访问  "ROLE_LOGIN"只是一个标记
        return SecurityConfig.createList("ROLE_LOGIN");
    }


    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
