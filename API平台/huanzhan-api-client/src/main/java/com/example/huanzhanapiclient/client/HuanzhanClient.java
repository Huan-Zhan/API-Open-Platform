package com.example.huanzhanapiclient.client;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.huanzhanapiclient.Utils.Sign;
import com.example.huanzhanapiclient.domain.TranslationInformation;
import com.example.huanzhanapiclient.model.HuanzhanUser;
import lombok.Data;


import java.util.HashMap;
import java.util.Map;


public class HuanzhanClient {

    String secretKey ,accessKey ;

    public HuanzhanClient(String accessKey, String secretKey) {
        this.secretKey = secretKey;
        this.accessKey = accessKey;
    }

    public String getName(TranslationInformation information){

        String url = "http://localhost:8082/api/user/getname";
//        String url = "http://47.113.192.175:8082/api/user/getLoveWords";
        final String JSON = JSONUtil.toJsonStr(information);

        HttpResponse response = HttpRequest.post(url)
                                .addHeaders(getHeaderMapper())
                                .body(JSON)
                                .execute();

//        System.out.println(response.body()  );
        return response.body() ;
    }

    public String getPoisonousChickenSoup(TranslationInformation information){
        String url = "http://localhost:8082/api/user/getPoisonousChickenSoup";
//        String url = "http://47.113.192.175:8082/api/user/getLoveWords";
        final String JSON = JSONUtil.toJsonStr(information);
        HttpResponse response = HttpRequest.post(url)
                .addHeaders(getHeaderMapper())
                .body(JSON)
                .execute();

//        System.out.println(response.body());
        return response.body() ;
    }

    public String getLoveWords(TranslationInformation information){
        String url = "http://localhost:8082/api/user/getLoveWords";
//        String url = "http://47.113.192.175:8082/api/user/getLoveWords";

        final String JSON = JSONUtil.toJsonStr(information);
        HttpResponse response = HttpRequest.post(url)
                .addHeaders(getHeaderMapper())
                .body(JSON)
                .execute();

//        System.out.println(response.body());
        return response.body() ;
    }


    private Map<String, String> getHeaderMapper() {

        Map<String,String> hashmap = new HashMap<>();
        hashmap.put("accessKey",accessKey);
        hashmap.put("secretKey",Sign.getSign(secretKey));
        hashmap.put("none", RandomUtil.randomNumbers(3));
        hashmap.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
        hashmap.put("WhereAreYouFrom", "MyClient");

        return hashmap ;
    }

}
