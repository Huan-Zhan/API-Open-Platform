package com.example.huanzhanapicenter.controller;

import com.example.huanzhanapicenter.common.BaseResponse;
import com.example.huanzhanapicenter.common.ResultUtils;
import com.example.huanzhanapicenter.domain.AdminUser;
import com.example.huanzhanapicenter.domain.LoginUser;
import com.example.huanzhanapicenter.domain.RegisterUser;
import com.example.huanzhanapicenter.domain.ResponseUser;
import com.example.huanzhanapicenter.service.UserService;
import com.example.huanzhanapicenter.utils.Tools;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

/**
 * 用户系统接口
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    UserService service ;

    /**
     * hello 测试能不能访问到
     */
    @RequestMapping("/hello")
    public String Hello(){
        System.out.println("hello");
        return "Hello" ;
    }

    /**
     * 自动登录页面，凭借曾经的token 来登录
     * @param userName
     * @return
     */
    @PostMapping("/autologin")
    public BaseResponse AutoLogin(@RequestBody LoginUser userName){
        // 判断 userName 的值 是否为空
        if (userName == null) return ResultUtils.Error(null);
        // 2. 交给服务层
        ResponseUser responseUser = service.AutoLogin(userName);
        // 3. 判断服务层的数据
        if (responseUser == null) return ResultUtils.Error(null);

        // 4.获取当前时间 和上次时间作对比，如果相差时间在 2h 以上 则需要重新登录
        if (Tools.AcquireTodaySeconds()-responseUser.getLastDate() > 2*60*60 || responseUser.getLastDate() - Tools.AcquireTodaySeconds() > 2*60*60 ) return ResultUtils.OutTime();
        // 5. 返回
        return new ResultUtils().Success(responseUser);
    }

    /**
     * 用户登录接口
     * return BaseResponse<ResponseUser> ;
     */
    @PostMapping("/login")
    public BaseResponse Login(@RequestBody LoginUser user){

        // 判断user是否存在
        if (user.getUserName() == null || user.getUserPassword() == null){
            return ResultUtils.Error(user);
        }
        // 提交给服务层
        final ResponseUser responseUser = service.CheckUserIsExist(user);

        // 如果返回的 值 是null
        if(responseUser ==null) return ResultUtils.Error(null);

        // 一切都合法 返回
        return ResultUtils.Success(responseUser);

    }

    /**
     * 注册页面
     * @param user 注册信息
     * @return 用户信息
     */
    @PostMapping("/register")
    public BaseResponse Register(@RequestBody RegisterUser user){

        // 判断user是否存在
        if (user.getUserName() == null || user.getUserPassword() == null || user.getCheckPassword() == null || user.getInvitationCode()==null){
            return ResultUtils.Error(user);
        }
        // 提交给服务层 todo
        final ResponseUser responseUser = service.UserRegister(user);

        // 如果返回的 值 是null todo
        if(responseUser == null) return ResultUtils.Error(null);

        // todo
        return ResultUtils.Success(responseUser);
    }

    /**
     * 管理员查询用户信息
     * @param user 身份
     * @return 用户信息
     */
    @PostMapping("/AdminToGetUserInformation")
    public BaseResponse AdminToGetUserInformation(@RequestBody LoginUser user){
        // 1. 判断传入值 是否为空
        if (user == null) return ResultUtils.Error(null) ;
        // 2. 提交给服务层
        List<ResponseUser> responseUsers = service.AdminToGetUserInformation(user);
        // 3. 判断服务层返回的数据 是否为空
        if (responseUsers == null) return ResultUtils.Error(null) ;
        // 4. 返回给前端
        return ResultUtils.Success(responseUsers);
    }

    @PostMapping("/AdminToDeleteUser")
    public BaseResponse AdminToDeleteUser(@RequestBody AdminUser user ){
        // 1. 判断传入值 是否为空
        if (user == null) return ResultUtils.Error(null) ;
        // 2. 提交给服务层
        ResponseUser responseUser = service.AdminToDeleteUser(user);
        // 3. 判断服务层返回的数据 是否为空
        if (responseUser == null) return ResultUtils.Error(null) ;
        // 4. 返回给前端
        return ResultUtils.Success(null);
    }

    @PostMapping("/AdminToUpdate")
    public BaseResponse AdminToUpdate(@RequestBody AdminUser user){
        // 1. 判断传入值 是否为空
        if (user == null) return ResultUtils.Error(null) ;
//        // 2. 提交给服务层
        ResponseUser responseUser = service.AdminToUpdate(user);
//        // 3. 判断服务层返回的数据 是否为空
        if (responseUser == null) return ResultUtils.Error(null) ;
//        // 4. 返回给前端
        return ResultUtils.Success(null);

    }

}
