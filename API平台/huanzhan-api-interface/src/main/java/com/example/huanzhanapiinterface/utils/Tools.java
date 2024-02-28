package com.example.huanzhanapiinterface.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.example.huanzhanapiinterface.common.ResultUtils;

import javax.servlet.http.HttpServletRequest;

public class Tools {

    public static boolean CheckKey(String clientSecretKey ,String userSecretKey){

        return true ;
    }

    public static boolean CheckForm(HttpServletRequest request){

        if (!request.getHeader("WhereAreYouFrom").equals("MyClient")) return false;

        return true ;
    }

    public static String getSign(){
        String secretKey = "huanzhan" ;
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String testStr = "salt."+secretKey;
        String digestHex = md5.digestHex(testStr);
        return digestHex ;
    }



}
