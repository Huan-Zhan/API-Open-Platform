package com.example.huanzhanapiinterface.common;

/**
 * 后端接口响应前端标准 响应体
 * Success ： 成功响应
 * Error   ： 错误响应
 */

public class ResultUtils {

    public static <T>BaseResponse<T> Success(T data){
        return new BaseResponse<T>(BaseCode.Code_Achieve,data,BaseMessage.Message_Achieve);
    }
    public static <T>BaseResponse<T> Error(T data){
        return new BaseResponse<T>(BaseCode.Code_Error,data,BaseMessage.Message_Error);
    }
    public static <T>BaseResponse<T> OutTime(){
        return new BaseResponse<T>(BaseCode.Out_Times,null,BaseMessage.Message_Error);
    }
    public static <T>BaseResponse<T> Forbid(){
        return new BaseResponse<T>(BaseCode.Code_Forbid,null,BaseMessage.Message_Forbid);
    }
}
