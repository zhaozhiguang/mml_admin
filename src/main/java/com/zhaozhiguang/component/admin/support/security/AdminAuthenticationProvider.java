package com.zhaozhiguang.component.admin.support.security;

import com.zhaozhiguang.component.admin.entity.AdminAuthenticationToken;
import com.zhaozhiguang.component.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 后台验证Provider
 * @author zhaozhiguang
 */
@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AdminAuthenticationToken token = (AdminAuthenticationToken) authentication;
        UserDetails userDetails = adminService.loadByUserName(token.getName());
        if(userDetails==null){
            throw new UsernameNotFoundException("用户没找到");
        }
        if(authentication.getCredentials() == null || !bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())){
            throw new BadCredentialsException("密码错误");
        }
        if(userDetails.isAccountNonExpired()){
            throw new AccountExpiredException("账号已过期");
        }
        if(userDetails.isAccountNonLocked()){
            throw new LockedException("账号已锁定");
        }
        if(userDetails.isEnabled()){
            throw new DisabledException("账号已禁用");
        }
        return new AdminAuthenticationToken(userDetails, true);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(AdminAuthenticationToken.class);
    }

}
