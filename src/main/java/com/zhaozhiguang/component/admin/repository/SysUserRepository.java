package com.zhaozhiguang.component.admin.repository;

import com.zhaozhiguang.component.admin.entity.SysUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SysUserRepository extends PagingAndSortingRepository<SysUser, Integer> {

    SysUser findByUserName(String userName);

}
