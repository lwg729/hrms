package com.lwg.hrms.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author lwg
 * @Date 2020/12/21 17:22
 * @Version 1.0
 * 判断当前用户是否具备所需的角色
 */
@Component
public class CustomerUrlDecisionManager implements AccessDecisionManager {

    /**
     * @param authentication   当前登录的用户
     * @param object           请求的对象
     * @param configAttributes MyFilter中返回的角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        //遍历所有的url所需要的的角色
        for (ConfigAttribute configAttribute : configAttributes) {
            //每个url所需要的角色
            String needRole = configAttribute.getAttribute();

            //判断myFilter中最后标记的ROLE_LOGIN
            if ("ROLE_LOGIN".equals(needRole)) {
                //如何当前用户为匿名用户  表名未登录
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("尚未登录,请登录--------");
                } else { //如果为此角色,则直接返回,允许跳转
                    return;
                }
            }

            //判断进入登陆后的菜单选择的权限  获取当前登录用户的角色 一个用户不止一个角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }

        }
        throw new AccessDeniedException("权限不足,请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
