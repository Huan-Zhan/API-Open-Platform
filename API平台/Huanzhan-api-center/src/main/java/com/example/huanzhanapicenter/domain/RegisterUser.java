package com.example.huanzhanapicenter.domain;

import lombok.Data;

/**
 * UserName 用户名
 * Password 密码
 * CheckPassword 确认密码
 * InvitationCode 私人邀请码
 *
 */

@Data
public class RegisterUser {

    private String userName ;
    private String userPassword ;
    private String checkPassword ;
    private String invitationCode ;

}
