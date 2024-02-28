package com.example.huanzhanapicenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.huanzhanapicenter.domain.ApiInterfaceInformation;
import com.example.huanzhanapicenter.domain.Apicenter;
import com.example.huanzhanapicenter.domain.UserWithInterface;
import com.example.huanzhanapicenter.service.*;
import com.example.huanzhanapicenter.utils.Tools;
import com.example.huanzhanapiclient.client.HuanzhanClient;
import com.example.huanzhanapiclient.domain.TranslationInformation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 接口被调用
 * 用户调用接口方法的实现
 */
@Service
public class InvokedServiceImpl implements InvokedService {

    // 接口信息接口
    @Resource
    ApiService apiService ;
    @Resource
    UserService userService;
    @Resource
    UserWithInterfaceService userWithInterfaceService;
    @Resource
    HuanzhanClient client ;
    @Resource
    ApiInterfaceDataService dataService ;


    /**
     * 用户调用了某个接口
     * 让某个接口被调用次数 +1
     * @return
     */
    @Override
    public boolean UserInvokedInterface(UserWithInterface user) {
        // 1.判断 userName 和 interfaceName 的信息
        if (user.getInterfacename()==null || user.getUsername()==null)return false;

        // 2.去数据库中搜索该数据 如果不存则创建 ， 存在则加一 ，且设置
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("UserName",user.getUsername());
        wrapper.eq("InterfaceName",user.getInterfacename());
        UserWithInterface one = userWithInterfaceService.getOne(wrapper);


        // todo
        // 判断用户状态 和 接口状态

        if (!userService.CheckUserStatus(user.getUsername()) || !apiService.CheckInterfaceStatus(user.getInterfacename())){
            return false;
        }

        if (one==null){
            // 生成
            this.CreateUserWithInterface(user);
            one = userWithInterfaceService.getOne(wrapper);
        }

        // 1.接口总调用次数 +1
        apiService.ApiAddInvokedNumber(one.getInterfacename());
        // 2.该用户调用该接口 次数 +1
        one.setInvokednumber(one.getInvokednumber()+1);
        // 3.该用户剩余调用次数 -1
        one.setLeftnumber(one.getLeftnumber()-1);

        final boolean update = userWithInterfaceService.update(one, wrapper);

        return update;
    }

    /**
     * 创建  user 和 interface 的关系
     * @param user
     * @return
     */
    @Override
    public boolean CreateUserWithInterface(UserWithInterface user) {
        // 1. 判断 userName 和 interfaceName 是否为空
        if(user.getUsername()==null || user.getInterfacename()==null)return false;

        // 2. 获取 该接口信息 ， 判断该接口状态
        ApiInterfaceInformation interfaceInformation = apiService.getOneInformation(user.getInterfacename());
        if (interfaceInformation==null || interfaceInformation.getInterfacestatus()==2)return false;

        // 3. 获取 该用户信息 ， 判断该用户状态
        Apicenter oneUserInformation = userService.getOneUserInformation(user.getUsername());
        if (oneUserInformation == null )return false;

        // 4.装配类别 ， 存储数据
        UserWithInterface userWithInterface = new UserWithInterface();
        userWithInterface.setUsername(user.getUsername());
        userWithInterface.setUserstatus(oneUserInformation.getIsdelete());
        userWithInterface.setInterfacename(user.getInterfacename());
        userWithInterface.setInterfacestatus(interfaceInformation.getInterfacestatus());
        userWithInterface.setInvokednumber(0L);
        userWithInterface.setLeftnumber(100L);

        final boolean save = userWithInterfaceService.save(userWithInterface);

        return save;
    }

    @Override
    public String InvokeGetUserName(TranslationInformation user) {

        TranslationInformation information =  new TranslationInformation();





        information.setUserName(user.getUserName());
        information.setNumbers(user.getNumbers());
        information.setAccessKey(user.getAccessKey());
        information.setSecretKey(user.getUserName());

        final String result = client.getName(information);

        System.out.println("Name的 result 是 : " +result);

        // 判断调用是否成功
        // 调用UserInvokedInterface 方法 ， 更新数据
        if (Tools.StringGetCode(result) == 200){

            UserWithInterface userWithInterface = new UserWithInterface();
            userWithInterface.setUsername(user.getUserName());
            userWithInterface.setInterfacename("getName");
            this.UserInvokedInterface(userWithInterface);

        }
        System.out.println("毒鸡汤的 name 是 : " +Tools.StringGetName(result));

        return Tools.StringGetName(result);
    }

    @Override
    public String InvokeGetPoisonousChickenSoup(TranslationInformation user) {
        TranslationInformation information =  new TranslationInformation();

        information.setUserName(user.getUserName());
        information.setNumbers(user.getNumbers());
        information.setAccessKey(user.getAccessKey());
        information.setSecretKey(user.getUserName());

        final String result = client.getPoisonousChickenSoup(information);
        System.out.println("毒鸡汤的 result 是 : " +result);
        // 判断调用是否成功
        // 调用UserInvokedInterface 方法 ， 更新数据
        if (Tools.StringGetCode(result) == 200){
            UserWithInterface userWithInterface = new UserWithInterface();
            userWithInterface.setUsername(user.getUserName());
            userWithInterface.setInterfacename("getPoisonousChickenSoup");
            this.UserInvokedInterface(userWithInterface);
        }

        final int idx = Tools.StringToInt(result);
        System.out.println("毒鸡汤的 随机数 是 : " +idx);

        final String oneData = dataService.getOneData((Long) (idx % 20 + 1L));


        return oneData;
    }

    @Override
    public String InvokeGetLoveWords(TranslationInformation user) {
        TranslationInformation information =  new TranslationInformation();

        information.setUserName(user.getUserName());
        information.setNumbers(user.getNumbers());
        information.setAccessKey(user.getAccessKey());
        information.setSecretKey(user.getUserName());

        final String result = client.getLoveWords(information);
        System.out.println("土味情话的 result 是 : " +result);
        // 判断调用是否成功
        // 调用UserInvokedInterface 方法 ， 更新数据
        if (Tools.StringGetCode(result) == 200){
            UserWithInterface userWithInterface = new UserWithInterface();
            userWithInterface.setUsername(user.getUserName());
            userWithInterface.setInterfacename("getLoveWords");
            this.UserInvokedInterface(userWithInterface);
        }

        final int idx = Tools.StringToInt(result);
        System.out.println("土味情话的 随机数 是 : " +idx);
        final String oneData = dataService.getOneData((Long) (idx %20 + 21L));


        return oneData;
    }
}
