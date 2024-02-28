package com.example.huanzhanapicenter.domain;

import lombok.Data;

@Data
public class RequestApiInformation {
    private String userName ;
    private Long id;
    private String interfacename;
    private String method;
    private String interfacefunction;
    private String interfacestatus;
    private String onlineaddress;
    private String requestparams;
    private String respondtype;
    private String responddata;
    private Long invokednumber;

    public RequestApiInformation() {
    }

    public RequestApiInformation(ApiInterfaceInformation information) {
        if (information.getId()!=null)this.id = information.getId();
        if (information.getInterfacename()!=null)this.interfacename = information.getInterfacename();
        if (information.getMethod()!=null)this.method = information.getMethod();
        if (information.getInterfacefunction()!=null)this.interfacefunction = information.getInterfacefunction();

        if (information.getInterfacestatus() != null){
            if (information.getInterfacestatus() == 0) this.interfacestatus = "正常";
            if (information.getInterfacestatus() == 1) this.interfacestatus = "待上线";
            if (information.getInterfacestatus() == 2) this.interfacestatus = "异常";
        }

        if (information.getOnlineaddress()!=null)this.onlineaddress = information.getOnlineaddress();
        if (information.getRequestparams()!=null)this.requestparams = information.getRequestparams();
        if (information.getRespondtype()!=null)this.respondtype =information.getRespondtype();
        if (information.getResponddata()!=null)this.responddata = information.getResponddata();
        if (information.getInvokednumber()!=null)this.invokednumber = information.getInvokednumber() ;
    }
}
