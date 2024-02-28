package com.example.huanzhanapicenter.service;


import com.example.huanzhanapicenter.domain.UserWithInterface;
import com.example.huanzhanapiclient.domain.TranslationInformation;

/**
 * 接口被调用
 * 用户调用接口方法的实现
 */
public interface InvokedService {
    boolean UserInvokedInterface(UserWithInterface user);
    boolean CreateUserWithInterface(UserWithInterface user);
    String InvokeGetUserName(TranslationInformation user);
    String InvokeGetPoisonousChickenSoup(TranslationInformation user);
    String InvokeGetLoveWords(TranslationInformation user);
}
