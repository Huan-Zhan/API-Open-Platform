package com.example.huanzhanapicenter.domain;

import lombok.Data;

/**
 * 携带 管理员名字的用户信息
 */
@Data
public class AdminUser {
    private String adminName;
    private String UserName ;
    private String AvatarUrl ;
    private String Phone ;
    private Long   LastDate ;
    private int    NoneNumber ;
    private String Gender ;
}
