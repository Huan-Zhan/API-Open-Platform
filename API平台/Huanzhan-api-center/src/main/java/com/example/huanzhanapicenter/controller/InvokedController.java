package com.example.huanzhanapicenter.controller;


import com.example.huanzhanapicenter.common.BaseResponse;
import com.example.huanzhanapicenter.common.ResultUtils;
import com.example.huanzhanapicenter.service.InvokedService;
import com.example.huanzhanapicenter.utils.Tools;
import com.example.huanzhanapiclient.domain.TranslationInformation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * api 被调用 控制类
 */
@RestController
@RequestMapping("/invoke")
public class InvokedController {

    @Resource
    InvokedService service ;

    @RequestMapping("/user/getname")
    public BaseResponse InvokeGetUserName(@RequestBody TranslationInformation information){

        if (information.getUserName()==null) return null;
        information.setAccessKey("huanzhan");
        information.setSecretKey("huanzhan");
        final String s = service.InvokeGetUserName(information);

        return ResultUtils.Success(s);
    }

    @RequestMapping("/user/getPoisonousChickenSoup")
    public BaseResponse InvokeGetPoisonousChickenSoup(@RequestBody TranslationInformation information){
        if (information.getUserName()==null) return null;
        information.setAccessKey("huanzhan");
        information.setSecretKey("huanzhan");
        final String s = service.InvokeGetPoisonousChickenSoup(information);


        return ResultUtils.Success(s);
    }

    @RequestMapping("/user/getLoveWords")
    public BaseResponse InvokeGetLoveWords(@RequestBody TranslationInformation information){
        if (information.getUserName()==null) return null;
        information.setAccessKey("huanzhan");
        information.setSecretKey("huanzhan");
        final String s = service.InvokeGetLoveWords(information);

        return ResultUtils.Success(s);
    }


    /**
     * 1.毒鸡汤              id : 1-20
     * 2.土味情话            id : 21-40
     * 3.随机图片            id : 41-60
     * 4.当前用户的用户名     id :  61- 80
     * 5.六爻 各爻 阴阳属性   id :  81 - 80+2^6
     */

}
