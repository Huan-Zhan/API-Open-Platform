package com.example.huanzhanapicenter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.huanzhanapicenter.common.BaseMessage;
import com.example.huanzhanapicenter.domain.*;
import com.example.huanzhanapicenter.service.ApicenterService;
import com.example.huanzhanapicenter.service.UserService;
import com.example.huanzhanapicenter.utils.Tools;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class HuanzhanApiCenterApplicationTests {

    @Resource
    ApicenterService service ;
    @Resource
    UserService userService ;

    @Test
    void contextLoads() {
    }

    /**
     * 检测数据库是否可用
     */
    @Test
    void TestDatasource(){

        Apicenter user = new Apicenter() ;
        user.setUsername("huanzhan");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userName",user.getUsername());
        final Apicenter checkUser = service.getOne(wrapper);

        System.out.println(checkUser.toString());
    }

    /**
     * 测试工具类方法 AcquireTodaySeconds 是否达到预期效果
     */
    @Test
    void TestToolsCurrentTimes(){

        System.out.println(Tools.AcquireTodaySeconds());

    }

    /**
     * 测试工具方法 是否生效
     */

    @Test
    void TestCharIsPassing(){

        final boolean result = Tools.CharIsPassing("huanzhan");

        System.out.println(result);

    }

    /**
     * 测试注册
     */
    @Test
    void TestRegister(){

        RegisterUser user = new RegisterUser();
        user.setUserName("admin");
        user.setUserPassword("123456789");
        user.setCheckPassword("123456789");
        user.setInvitationCode(BaseMessage.Invitation_Code);


        // 1. 账号长度不够
        user.setUserName("ad");
        ResponseUser responseUser = userService.UserRegister(user);
        Assert.isTrue(responseUser == null);
        // 2. 密码长度不够
        user.setUserName("admin");
        user.setUserPassword("12");
        user.setCheckPassword("12");
        responseUser = userService.UserRegister(user);
        Assert.isTrue(responseUser == null);
        // 3. 确认密码错误
        user.setUserPassword("123456789");
        user.setCheckPassword("1234567891");
        responseUser = userService.UserRegister(user);
        Assert.isTrue(responseUser == null);
        // 4. 账号 字符 不合法
        user.setCheckPassword("123456789");
        user.setUserName("admin王锐");
        responseUser = userService.UserRegister(user);
        Assert.isTrue(responseUser == null);
        // 5. 密码 字符 不合法
        user.setUserName("admin");
        user.setUserPassword("123456789@");
        user.setCheckPassword("123456789@");
        responseUser = userService.UserRegister(user);
        Assert.isTrue(responseUser == null);
        // 6. 用户名重复
        user.setUserName("huanzhan");
        user.setUserPassword("123456789");
        user.setCheckPassword("123456789");
        responseUser = userService.UserRegister(user);
        Assert.isTrue(responseUser == null);
        // 7. 成功案例
        user.setUserName("admin");
        user.setUserPassword("123456789");
        user.setCheckPassword("123456789");
        user.setInvitationCode(BaseMessage.Invitation_Code);
        responseUser = userService.UserRegister(user);
        Assert.isTrue(responseUser != null);

    }

    @Test
    void TestAdminToGetUserInformation(){
        LoginUser user = new LoginUser();
        user.setUserName("admin");
        final List<ResponseUser> responseUsers = userService.AdminToGetUserInformation(user);

        if (responseUsers == null) System.out.println("返回为空");

        else System.out.println(responseUsers.toString());

    }

    @Test
    void TestAdminToDelete(){
        AdminUser user = new AdminUser();

        user.setAdminName("admin");
        user.setUserName("dsada");

        final ResponseUser responseUser = userService.AdminToDeleteUser(user);

        if (responseUser == null) System.out.println("返回为空");

        else System.out.println(responseUser.toString());

    }
}
