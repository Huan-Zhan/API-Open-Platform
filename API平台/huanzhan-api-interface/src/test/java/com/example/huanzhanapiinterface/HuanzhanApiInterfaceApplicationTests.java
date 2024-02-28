package com.example.huanzhanapiinterface;

import com.example.huanzhanapiclient.client.HuanzhanClient;
import com.example.huanzhanapiclient.domain.TranslationInformation;
import com.example.huanzhanapiclient.model.HuanzhanUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class HuanzhanApiInterfaceApplicationTests {

    @Resource
    HuanzhanClient client ;

    @Test
    void ClientTest(){

        System.out.println(client);
        TranslationInformation information = new TranslationInformation();
        information.setUserName("huanzhan");
        final String name = client.getName(information);

        System.out.println(name);
    }


    @Test
    void contextLoads() {

    }

}
