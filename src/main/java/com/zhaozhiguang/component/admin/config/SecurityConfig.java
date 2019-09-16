package com.zhaozhiguang.component.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
//@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig() {
        super(true);//禁用默认配置
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
                .anyRequest().authenticated().and()
                .exceptionHandling().and()
                .headers().and()
                .securityContext().and()
                .anonymous().and()
                .csrf().disable()
                //.formLogin().and()
                .httpBasic();

        //http.removeConfigurer(FormLoginConfigurer.class);
    }
}
