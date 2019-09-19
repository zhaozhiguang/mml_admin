package com.zhaozhiguang.component.admin.service.impl;

import com.zhaozhiguang.component.admin.entity.SysUser;
import com.zhaozhiguang.component.admin.repository.*;
import com.zhaozhiguang.component.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private SysPermissionsRepository sysPermissionsRepository;

    @Autowired
    private SysDeptRepository sysDeptRepository;

    @Autowired
    private SysDictRepository sysDictRepository;

    @Override
    public SysUser loadByUserName(String userName) {
        return sysUserRepository.findByUserName(userName);
    }
}
