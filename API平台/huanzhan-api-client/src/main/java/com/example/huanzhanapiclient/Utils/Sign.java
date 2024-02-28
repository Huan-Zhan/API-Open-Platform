package com.example.huanzhanapiclient.Utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class Sign {

    public static String getSign(String secretKey){
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String testStr = "salt."+secretKey;
        String digestHex = md5.digestHex(testStr);
        return digestHex ;
    }

}
