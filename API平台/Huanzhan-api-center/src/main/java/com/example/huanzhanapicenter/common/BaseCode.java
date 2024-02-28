package com.example.huanzhanapicenter.common;

import lombok.Data;

/**
 *  code 响应码
 *  200 成功
 *  415 参数错误
 */
@Data
public class BaseCode {
    public static final String Code_Achieve= "200" ;
    public static final String Code_Error= "415" ;
    public static final String Out_Times= "720s" ;

}
