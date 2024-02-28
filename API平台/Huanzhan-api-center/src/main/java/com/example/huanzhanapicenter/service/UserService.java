package com.example.huanzhanapicenter.service;

import com.example.huanzhanapicenter.domain.*;

import java.util.List;

/**
 * 用户系统方法接口
 */
public interface UserService {

    ResponseUser CheckUserIsExist(LoginUser user);
    ResponseUser AutoLogin(LoginUser userName);
    ResponseUser UserRegister(RegisterUser user);
    List<ResponseUser> AdminToGetUserInformation(LoginUser user);

    ResponseUser AdminToDeleteUser(AdminUser user);
    ResponseUser AdminToUpdate(AdminUser user);

    Apicenter getOneUserInformation(String userName);

    boolean CheckUserStatus(String userName);

}
