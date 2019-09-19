package com.zhaozhiguang.component.admin.support.mvc;

import java.lang.annotation.*;

/**
 * mvc拦截参数,验证码
 */
@Documented
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Captcha {

}
