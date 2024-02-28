package com.example.huanzhanapicenter.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName api_interface_params
 */
@TableName(value ="api_interface_params")
@Data
public class ApiInterfaceParams implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "InterfaceName")
    private String interfacename;

    /**
     * 
     */
    @TableField(value = "params")
    private String params;

    /**
     * 
     */
    @TableField(value = "paramsValue")
    private String paramsvalue;

    /**
     * 0 非必须 ， 1 必须
     */
    @TableField(value = "isMust")
    private Integer ismust;

    /**
     * 
     */
    @TableField(value = "description")
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}