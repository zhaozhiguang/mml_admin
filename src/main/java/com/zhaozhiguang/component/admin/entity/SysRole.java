package com.zhaozhiguang.component.admin.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 角色
 *
 */
@Data
@Entity
@Table(name = "sys_role")
public class SysRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名
     */
    @Column(name = "role_name", nullable = false)
    private String roleName;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false)
    private Integer status = 0;

    /**
     * 排序
     */
    @Column(name = "order_by", nullable = false)
    private Integer orderBy = 0;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    @CreatedDate
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time", nullable = false)
    @LastModifiedDate
    private LocalDateTime updateTime;

    @Column(name = "version")
    @Version
    private Integer version;

}
