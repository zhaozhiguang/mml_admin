package com.zhaozhiguang.component.admin.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 用户
 * @author zhaozhiguang
 */
@Data
@Entity
@Table(name = "sys_dept", indexes = { @Index(name = "parent_id_index", columnList = "parent_id")})
public class SysDept {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 简称
     */
    @Column(name = "simple_name", nullable = false)
    private String simpleName;

    /**
     * 全称
     */
    @Column(name = "full_name", nullable = false)
    private String fullName;

    /**
     * 父级Id
     */
    @Column(name = "parent_id", nullable = false)
    private Integer parentId;

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

    /**
     * 排序
     */
    @Column(name = "order_by", nullable = false)
    private Integer orderBy = 0;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false)
    private Integer status = 0;

    @Column(name = "version")
    @Version
    private Integer version;

}
