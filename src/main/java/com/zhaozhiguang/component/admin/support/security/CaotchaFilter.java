package com.zhaozhiguang.component.admin.support.security;

import com.zhaozhiguang.component.admin.exception.IncorrectCaptchaException;
import com.zhaozhiguang.component.jwt.JWT;
import com.zhaozhiguang.component.jwt.algorithms.Algorithm;
import com.zhaozhiguang.component.jwt.interfaces.DecodedJWT;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CaotchaFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {



    }

    /**
     * 验证码是否正确
     * @param request
     * @return
     */
    public void verify(HttpServletRequest request) throws IncorrectCaptchaException {
        String expected = request.getParameter("captchaEncrypt");
        //获取用户页面输入的验证码
        String received = request.getParameter("captcha");
        if(received == null || expected  == null) throw new IncorrectCaptchaException();
        try {
            DecodedJWT captcha = JWT.require(Algorithm.HMAC256("captcha")).build().verify(expected);
            if(!received.equalsIgnoreCase(captcha.getSubject())) throw new IncorrectCaptchaException();
        } catch (Exception e) {
            throw new IncorrectCaptchaException();
        }
    }

}
