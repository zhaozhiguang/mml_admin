package com.zhaozhiguang.component.admin.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户
 * @author zhaozhiguang
 */
@Data
@Entity
@Table(name = "sys_dept", indexes = { @Index(name = "parent_id_index", columnList = "parent_id")})
public class SysDict {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "dict_name", nullable = false)
    private String dictName;

    /**
     * 父级Id
     */
    @Column(name = "parent_id", nullable = false)
    private Integer parentId;

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

}
