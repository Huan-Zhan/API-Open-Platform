package com.example.huanzhanapicenter.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user_with_interface
 */
@TableName(value ="user_with_interface")
@Data
public class UserWithInterface implements Serializable {
    /**
     * 数据id

     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "UserName")
    private String username;

    /**
     * 0 正常 ， 1 异常
     */
    @TableField(value = "UserStatus")
    private Integer userstatus;

    /**
     * 接口名字

     */
    @TableField(value = "InterfaceName")
    private String interfacename;

    /**
     * 0 正常 ， 1 即将上线 ， 2 下线
     */
    @TableField(value = "InterfaceStatus")
    private Integer interfacestatus;

    /**
     * 该用户调用此接口次数
     */
    @TableField(value = "InvokedNumber")
    private Long invokednumber;

    /**
     * 剩余调用次数，没人默认100次调用次数
     */
    @TableField(value = "LeftNumber")
    private Long leftnumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}