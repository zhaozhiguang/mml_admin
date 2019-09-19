package com.zhaozhiguang.component.admin.support.mvc;

import com.zhaozhiguang.component.admin.exception.IncorrectCaptchaException;
import com.zhaozhiguang.component.jwt.JWT;
import com.zhaozhiguang.component.jwt.algorithms.Algorithm;
import com.zhaozhiguang.component.jwt.interfaces.DecodedJWT;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证验证码
 */
@Component
public class CaptchaResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(Captcha.class);//通过注解方式
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest servletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String received = servletRequest.getParameter("captcha"); //用户输入的验证码
        String expected = servletRequest.getParameter("captchaEncrypt"); //验证码token
        if (expected == null || expected.isEmpty()) expected = servletRequest.getHeader("captchaEncrypt");
        if(received == null || expected  == null) throw new IncorrectCaptchaException();
        try {
            DecodedJWT captcha = JWT.require(Algorithm.HMAC256("captcha")).build().verify(expected);
            if(!received.equalsIgnoreCase(captcha.getSubject())) throw new IncorrectCaptchaException();
        } catch (Exception e) {
            throw new IncorrectCaptchaException();
        }
        return received;
    }
}
