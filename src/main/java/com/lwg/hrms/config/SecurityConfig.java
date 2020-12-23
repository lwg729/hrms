package com.lwg.hrms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lwg.hrms.model.Hr;
import com.lwg.hrms.service.impl.HrServiceImpl;
import com.lwg.hrms.untils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.CredentialNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author lwg
 * @Date 2020/12/8 11:03
 * @Version 1.0
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HrServiceImpl hrService;

    @Autowired
    private CustomerUrlDecisionManager customerUrlDecisionManager;

    @Autowired
    private CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //要有一个configure方法吧hrService整进来
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico", "/verifyCode", "/login");
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                //.anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(customerUrlDecisionManager);
                        object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        return object;
                    }
                })
                .and()
                //表单登录
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin") //进入登录页面要输入的url
                .loginPage("/login")
                //登录成功的处理
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        //往外写
                        PrintWriter out = resp.getWriter();
                        //登录成功的对象
                        Hr hr = (Hr) authentication.getPrincipal();
                        hr.setPassword(null);
                        RespBean respBean = RespBean.ok("登陆成功", hr);
                        String s = new ObjectMapper().writeValueAsString(respBean);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        RespBean error = RespBean.error("登录失败");
                        if (e instanceof LockedException) {
                            error.setMsg("账户被锁定,请联系管理员");
                        } else if (e instanceof CredentialsExpiredException) {
                            error.setMsg("密码已过期,请联系管理员");
                        } else if (e instanceof AccountExpiredException) {
                            error.setMsg("账号已过期,请联系管理员");
                        } else if (e instanceof DisabledException) {
                            error.setMsg("账户被禁用,请联系管理员");
                        } else if (e instanceof BadCredentialsException) {
                            error.setMsg("用户名或者密码错误,请重新输入");
                        }
                        out.write(new ObjectMapper().writeValueAsString(error));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        RespBean respBean = RespBean.ok("注销成功");
                        String s = new ObjectMapper().writeValueAsString(respBean);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest req, HttpServletResponse resp,
                                 AuthenticationException authException) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                RespBean error = RespBean.error("访问失败");
                if (authException instanceof InsufficientAuthenticationException) {
                    error.setMsg("非法路径访问,请登录后再进行操作!!!");
                }
                out.write(new ObjectMapper().writeValueAsString(error));
                out.flush();
                out.close();
            }
        });
    }
}
