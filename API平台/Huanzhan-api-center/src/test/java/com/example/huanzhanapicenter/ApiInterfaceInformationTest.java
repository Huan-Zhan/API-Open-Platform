package com.example.huanzhanapicenter;


import com.example.huanzhanapicenter.domain.ApiInterfaceData;
import com.example.huanzhanapicenter.domain.ApiInterfaceInformation;
import com.example.huanzhanapicenter.domain.ResponseApiInformation;
import com.example.huanzhanapicenter.service.ApiInterfaceDataService;
import com.example.huanzhanapicenter.service.impl.ApiServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ApiInterfaceInformationTest {

    @Resource
    ApiServiceImpl apiService;

    @Resource
    ApiInterfaceDataService dataService ;

    /**
     * 测试 添加新的接口信息
     */
    @Test
    void TestCreatedInterface(){

        ApiInterfaceInformation information = new ApiInterfaceInformation();

        information.setInterfacename("getLoveWords");
        information.setMethod("POST");
        information.setInterfacefunction("返回随机一个土味情话");
        information.setInterfacestatus(0);
        information.setOnlineaddress("/user/getLoveWords");
        information.setRequestparams("");
        information.setRespondtype("");
        information.setResponddata("随机一个土味情话");
        information.setInvokednumber(0L);


        final Boolean aBoolean = apiService.addOneInformation(information);

        Assertions.assertTrue(aBoolean);

    }
    @Test
    void TestAddApiInvokedNumber(){

        final boolean getName = apiService.ApiAddInvokedNumber("getName");

        Assertions.assertTrue(getName);

    }

    @Test
    void TestgetInformation(){

        final List<ResponseApiInformation> information = apiService.getInformation("admin");

        Assertions.assertTrue(information != null);

        System.out.println(information.toString());

    }

    @Test
    void TestInsertData(){
        ApiInterfaceData data = new ApiInterfaceData();

        data.setInterfacedata("别减肥了，你丑不仅是因为胖。");

        final boolean save = dataService.save(data);

        Assertions.assertTrue(save);

    }

}
