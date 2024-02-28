package com.example.huanzhanapicenter;


import com.example.huanzhanapicenter.domain.UserWithInterface;
import com.example.huanzhanapicenter.service.InvokedService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserWithInterfaceTest {

    @Resource
    InvokedService invokedService;

    @Test
    void TestCreateUserWithInterface(){
        UserWithInterface user = new UserWithInterface();
        user.setUsername("LiBai");
        user.setInterfacename("getName");
        final boolean b = invokedService.UserInvokedInterface(user);

        Assertions.assertTrue(b);

    }
}
