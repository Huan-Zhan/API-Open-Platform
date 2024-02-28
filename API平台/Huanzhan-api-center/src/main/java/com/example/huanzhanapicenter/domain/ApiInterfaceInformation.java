package com.example.huanzhanapicenter.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName api_interface_information
 */
@TableName(value ="api_interface_information")
@Data
public class ApiInterfaceInformation implements Serializable {
    /**
     * 
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Long id;

    /**
     * 接口名称

     */
    @TableField(value = "InterfaceName")
    private String interfacename;

    /**
     * 调用方法 post 或者 get

     */
    @TableField(value = "Method")
    private String method;

    /**
     * 接口功能
     */
    @TableField(value = "InterfaceFunction")
    private String interfacefunction;

    /**
     * 接口状态
     * 0 上线 ， 1 准备上线 ， 2 下线
     */
    @TableField(value = "InterfaceStatus")
    private Integer interfacestatus;

    /**
     * 接口地址
     */
    @TableField(value = "OnlineAddress")
    private String onlineaddress;

    /**
     * 请求参数
     */
    @TableField(value = "RequestParams")
    private String requestparams;

    /**
     * 返回 or 响应 数据类型 默认json

     */
    @TableField(value = "RespondType")
    private String respondtype;

    /**
     * 返回数据说明
     * 返回数据的数据是什么
     */
    @TableField(value = "RespondData")
    private String responddata;

    /**
     * 接口被调用次数
     */
    @TableField(value = "InvokedNumber")
    private Long invokednumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public ApiInterfaceInformation() {
    }

    public ApiInterfaceInformation(ResponseApiInformation information) {
        if (information.getId()!=null)this.id = information.getId();
        if (information.getInterfacename()!=null)this.interfacename = information.getInterfacename();
        if (information.getMethod()!=null)this.method = information.getMethod();
        if (information.getInterfacefunction()!=null)this.interfacefunction = information.getInterfacefunction();

        if (information.getInterfacestatus() != null){
            if (information.getInterfacestatus().equals("正常")) this.interfacestatus = 0;
            if (information.getInterfacestatus().equals("待上线")) this.interfacestatus = 1;
            if (information.getInterfacestatus().equals("异常")) this.interfacestatus = 2;
        }

        if (information.getOnlineaddress()!=null)this.onlineaddress = information.getOnlineaddress();
        if (information.getRequestparams()!=null)this.requestparams = information.getRequestparams();
        if (information.getRespondtype()!=null)this.respondtype =information.getRespondtype();
        if (information.getResponddata()!=null)this.responddata = information.getResponddata();
        if (information.getInvokednumber()!=null)this.invokednumber = information.getInvokednumber() ;
    }
    public ApiInterfaceInformation(RequestApiInformation information) {
        if (information.getId()!=null)this.id = information.getId();
        if (information.getInterfacename()!=null)this.interfacename = information.getInterfacename();
        if (information.getMethod()!=null)this.method = information.getMethod();
        if (information.getInterfacefunction()!=null)this.interfacefunction = information.getInterfacefunction();

        if (information.getInterfacestatus() != null){
            if (information.getInterfacestatus().equals("正常")) this.interfacestatus = 0;
            if (information.getInterfacestatus().equals("待上线")) this.interfacestatus = 1;
            if (information.getInterfacestatus().equals("异常")) this.interfacestatus = 2;
        }

        if (information.getOnlineaddress()!=null)this.onlineaddress = information.getOnlineaddress();
        if (information.getRequestparams()!=null)this.requestparams = information.getRequestparams();
        if (information.getRespondtype()!=null)this.respondtype =information.getRespondtype();
        if (information.getResponddata()!=null)this.responddata = information.getResponddata();
        if (information.getInvokednumber()!=null)this.invokednumber = information.getInvokednumber() ;
    }
}