package com.example.huanzhanapiinterface.controller;


import ch.qos.logback.core.spi.LogbackLock;
import cn.hutool.log.LogFactory;
import com.example.huanzhanapiclient.client.HuanzhanClient;


import com.example.huanzhanapiclient.domain.TranslationInformation;
import com.example.huanzhanapiinterface.common.BaseResponse;
import com.example.huanzhanapiinterface.common.ResultUtils;

import com.example.huanzhanapiinterface.utils.Tools;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class NameController {
    @Resource
    HuanzhanClient client;

    /**
     * 获取用户名
     * @param information
     * @param request
     * @return
     */
    @RequestMapping("/getname")
    public BaseResponse getName(@RequestBody TranslationInformation information , HttpServletRequest request){

        String from = request.getHeader("WhereAreYouFrom");
        String secretKey = request.getHeader("secretKey");



        if (!secretKey.equals(Tools.getSign()))return  ResultUtils.Forbid();
        if (!from.equals("MyClient")) return ResultUtils.Forbid();

        System.out.println("你的名字是 " + information.getUserName());

        System.out.println(" getname 请求成功");

        return ResultUtils.Success("你的名字是 " + information.getUserName() );
    }

    /**
     * 随机一个毒鸡汤
     * @param information
     * @param request
     * @return
     */
    @RequestMapping("/getPoisonousChickenSoup")
    public BaseResponse getPoisonousChickenSoup(TranslationInformation information, HttpServletRequest request){
        String from = request.getHeader("WhereAreYouFrom");
        if (!from.equals("MyClient")) return ResultUtils.Forbid();
        String secretKey = request.getHeader("secretKey");
        if (!secretKey.equals(Tools.getSign()))return  ResultUtils.Forbid();

        Random random = new Random();

        information.setNumbers(information.getNumbers()+random.nextInt(5000));
        System.out.println(" getPoisonousChickenSoup 请求成功 ， 随机数是"+information.getNumbers());


        return ResultUtils.Success(information) ;
    }

    @RequestMapping("/getLoveWords")
    public BaseResponse getLoveWords(TranslationInformation information, HttpServletRequest request){
        String from = request.getHeader("WhereAreYouFrom");
        String secretKey = request.getHeader("secretKey");
        if (!secretKey.equals(Tools.getSign()))return  ResultUtils.Forbid();
        if (!from.equals("MyClient")) return ResultUtils.Forbid();


        Random random = new Random();

        information.setNumbers(information.getNumbers()+random.nextInt(5000));
        System.out.println(" getLoveWords 请求成功,随机数是"+information.getNumbers());
        return ResultUtils.Success(information) ;
    }


}
