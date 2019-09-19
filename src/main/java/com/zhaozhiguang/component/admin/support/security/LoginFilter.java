package com.zhaozhiguang.component.admin.support.security;

import com.alibaba.fastjson.JSONObject;
import com.zhaozhiguang.component.admin.entity.AdminAuthenticationToken;
import com.zhaozhiguang.component.admin.entity.SysUser;
import com.zhaozhiguang.component.admin.exception.IncorrectCaptchaException;
import com.zhaozhiguang.component.jwt.JWT;
import com.zhaozhiguang.component.jwt.algorithms.Algorithm;
import com.zhaozhiguang.component.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录拦截器
 */
@Component
public class LoginFilter extends AbstractAuthenticationProcessingFilter implements InitializingBean {

    public LoginFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    /**
     * 创建token
     * @param request
     * @return
     */
    private AdminAuthenticationToken createToken(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return new AdminAuthenticationToken(SysUser.of(username, password), false);
    }

    /**
     * 校验jwt
     * @param request
     * @return
     */
    private boolean checkJwt(HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        try {
            DecodedJWT sercet = JWT.require(Algorithm.HMAC256("sercet")).build().verify(token);
            SysUser user = JSONObject.toJavaObject((JSONObject)sercet.getParameters().get("user"), SysUser.class);
            SecurityContextHolder.getContext().setAuthentication(new AdminAuthenticationToken(user, true));
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        if (!this.requiresAuthentication(request, response)) {
            //如果不是登录地址,查询jwt
            if(checkJwt(request)) {
                this.unsuccessfulAuthentication(request, response, new BadCredentialsException("身份验证失败"));
            } else {
                try {
                    chain.doFilter(request, response);
                } catch (Exception e) {
                    this.unsuccessfulAuthentication(request, response, new UsernameNotFoundException("系统异常"));
                }
            }
        } else {
            Authentication authResult;
            try {
                authResult = this.attemptAuthentication(request, response);
                if (authResult == null) {
                    return;
                }
            } catch (InternalAuthenticationServiceException var8) {
                this.unsuccessfulAuthentication(request, response, var8);
                return;
            } catch (AuthenticationException var9) {
                this.unsuccessfulAuthentication(request, response, var9);
                return;
            }
            this.successfulAuthentication(request, response, chain, authResult);
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AdminAuthenticationToken token = createToken(request);
        return getAuthenticationManager().authenticate(token);
    }

}
