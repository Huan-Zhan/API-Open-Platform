package com.example.huanzhanapiinterface;


import com.example.huanzhanapiclient.client.HuanzhanClient;
import com.example.huanzhanapiclient.domain.TranslationInformation;



public class Main {


    public static void main(String[] args) {


        String secretKey = "huanzhan",accessKey ="huanzhan";
        HuanzhanClient client = new HuanzhanClient(accessKey,secretKey);


        TranslationInformation information = new TranslationInformation();
        information.setUserName("huanzhan");
        information.setNumbers(21);
        System.out.println("-------------------------------------------------------------");
        String result1 = client.getName(information);
        System.out.println(result1);
        System.out.println("-------------------------------------------------------------");
        String result2 = client.getPoisonousChickenSoup(information);
        System.out.println(result2);
        System.out.println("-------------------------------------------------------------");
        String result3 = client.getPoisonousChickenSoup(information);
        System.out.println(result3);


        return;

    }

}
