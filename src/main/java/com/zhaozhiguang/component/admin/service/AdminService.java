package com.zhaozhiguang.component.admin.service;

import com.zhaozhiguang.component.admin.entity.SysUser;

/**
 * 后端管理
 * @author
 */
public interface AdminService {

    SysUser loadByUserName(String userName);

}
