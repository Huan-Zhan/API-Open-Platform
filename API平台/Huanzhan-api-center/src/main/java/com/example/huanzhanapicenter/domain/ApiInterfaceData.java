package com.example.huanzhanapicenter.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName api_interface_data
 */
@TableName(value ="api_interface_data")
@Data
public class ApiInterfaceData implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "interfaceData")
    private String interfacedata;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}