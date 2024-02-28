package com.example.huanzhanapicenter.domain;

import lombok.Data;

@Data
/**
 * UserName 用户名
 * AvatarUrl 用户头像
 * Phone     用户电话
 * LastDate  最后一次调用时间
 * NoneNumber 随机数字
 * Gender      性别
 *
 */
public class ResponseUser {
    private String UserName ;
    private String AvatarUrl ;
    private String Phone ;
    private Long   LastDate ;
    private int    NoneNumber ;
    private String Gender ;

    public ResponseUser() {
    }

    public ResponseUser(Apicenter user) {
        if(user.getUsername() !=null)       this.setUserName(user.getUsername());
        if(user.getAvatarurl()!=null)       this.setAvatarUrl(user.getAvatarurl());
        if(user.getPhone() != null)         this.setPhone(user.getPhone());
        if (user.getLastdate() != null )    this.setLastDate(user.getLastdate());
        if (user.getNonenumber() != null)   this.setNoneNumber(user.getNonenumber());

        if (user.getGender() == null || user.getGender() == 1)
            this.setGender("男");
        else
            this.setGender("女");
    }
    public ResponseUser setApicenter(Apicenter user) {
        ResponseUser responseUser = new ResponseUser() ;
        if(user.getUsername() !=null)       responseUser.setUserName(user.getUsername());
        if(user.getAvatarurl()!=null)       responseUser.setAvatarUrl(user.getAvatarurl());
        if(user.getPhone() != null)         responseUser.setPhone(user.getPhone());
        if (user.getLastdate() != null )    responseUser.setLastDate(user.getLastdate());
        if (user.getNonenumber() != null)   responseUser.setNoneNumber(user.getNonenumber());

        if (user.getGender() == null || user.getGender() == 1)
            responseUser.setGender("男");
        else
            responseUser.setGender("女");

        return responseUser ;
    }
}
