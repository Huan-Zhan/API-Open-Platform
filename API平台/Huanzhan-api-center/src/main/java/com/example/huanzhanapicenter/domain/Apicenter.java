package com.example.huanzhanapicenter.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * api项目的用户信息表
 * 数据库表格
 * @TableName apicenter
 */
@TableName(value ="apicenter")
@Data
public class Apicenter implements Serializable {
    /**
     * 
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号
     */
    @TableField(value = "UserName")
    private String username;

    /**
     * 用户密码
     */
    @TableField(value = "UserPassword")
    private String userpassword;

    /**
     * 密钥账号
     */
    @TableField(value = "AccessKey")
    private String accesskey;

    /**
     * 密钥密码
     */
    @TableField(value = "SecretKey")
    private String secretkey;

    /**
     * 头像地址
     */
    @TableField(value = "AvatarUrl")
    private String avatarurl;

    /**
     * 电话号码
     */
    @TableField(value = "Phone")
    private String phone;

    /**
     * 定义自定义算法 ，将当前时间点 转化为秒 ，存入数据库
     * 0 - 86400s
     */
    @TableField(value = "LastDate")
    private Long lastdate;

    /**
     * 随机数
     */
    @TableField(value = "NoneNumber")
    private Integer nonenumber;

    /**
     * 0 存在 ，1 删除
     */
    @TableField(value = "IsDelete")
    private Integer isdelete;

    /**
     * 0 女 ， 1 男
     */
    @TableField(value = "gender")
    private Integer gender;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;



}