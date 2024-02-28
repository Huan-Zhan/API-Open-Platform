package com.example.huanzhanapiinterface.common;

import lombok.Data;

/**
 * 标准 响应体
 * @author HZ
 *
 * code : 响应状态码
 * data : 响应主题
 * message : 响应信息
 *
 */
@Data
public class BaseResponse<T>{
    private String code ;

    private T data ;

    private String message ;

    public BaseResponse() {
    }

    public BaseResponse(String code ,T data,String message) {
        this.code = code ;
        this.data = data ;
        this.message = message ;
    }

    public void AchieveResponse(T data){
        code = BaseCode.Code_Achieve ;
        this.data = data ;
        message = BaseMessage.Message_Achieve ;
    }
    public void ErrorResponse(){

        code = BaseCode.Code_Error ;
        this.data = null ;
        message = BaseMessage.Message_Error ;
    }

    public void CodeResponse(String code){
        this.code = code ;
        this.data = null ;
        message = BaseMessage.Message_Error ;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
