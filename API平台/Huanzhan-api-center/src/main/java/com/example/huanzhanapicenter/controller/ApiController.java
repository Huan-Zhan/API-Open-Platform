package com.example.huanzhanapicenter.controller;

import com.example.huanzhanapicenter.common.BaseResponse;
import com.example.huanzhanapicenter.common.ResultUtils;
import com.example.huanzhanapicenter.domain.AdminUser;
import com.example.huanzhanapicenter.domain.RequestApiInformation;
import com.example.huanzhanapicenter.domain.ResponseApiInformation;
import com.example.huanzhanapicenter.service.ApiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.huanzhanapicenter.domain.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * api 控制层
 */

@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    ApiService service ;

    /**
     * 查询接口信息
     * @param user
     * @return
     */
    @PostMapping("/AdminToGetApiInformation")
    BaseResponse adminToGetApiInformation(@RequestBody AdminUser user){
        // 检查 userName 的信息
        if (user.getUserName() == null)return ResultUtils.Error(null);
        // 提交给服务层
        final List<ResponseApiInformation> information = service.getInformation(user.getUserName());
        // 检查服务层数据
        if (information ==null)return ResultUtils.Error(null) ;
        // 返回数据
        return ResultUtils.Success(information) ;
    }

    @PostMapping("/UserToGetApiInformation")
    BaseResponse userToGetApiInformation(@RequestBody AdminUser user){
        // 检查 userName 的信息
        if (user.getUserName() == null)return ResultUtils.Error(null);
        // 提交给服务层
        final List<ResponseApiInformation> information = service.userGetInformation(user.getUserName());
        // 检查服务层数据
        if (information ==null)return ResultUtils.Error(null) ;
        // 返回数据
        return ResultUtils.Success(information) ;
    }

    @PostMapping("/AdminToUpdateInterfaceInformation")
    BaseResponse adminToUpdateInterfaceInformation(@RequestBody RequestApiInformation information){
        // 检查 userName
        if (information.getUserName() == null)return ResultUtils.Error(null);

        // 提交给服务层
        ApiInterfaceInformation interfaceInformation = new ApiInterfaceInformation(information);
        final boolean status = service.updateOneInformation(interfaceInformation);
        // 检查服务层数据
        if (status == false)return ResultUtils.Error(null);
        // 返回
        return ResultUtils.Success(true);
    }

    /**
     * 用户查询一个 接口信息
     * @param information
     * @return
     */
    @PostMapping("/UserToGetOneInterfaceInformation")
    BaseResponse UserToGetOneInterfaceInformation(@RequestBody RequestApiInformation information){
        // 检查 userName
        if (information.getUserName() == null || information.getInterfacename() ==null)return ResultUtils.Error(null);

        // 提交给服务层
        final ApiInterfaceInformation oneInformation = service.getOneInformation(information.getInterfacename());
        // 检查服务层数据
        if (oneInformation == null)return ResultUtils.Error(null);
        // 返回
        return ResultUtils.Success(oneInformation);
    }

    /**
     * 用户查询一个 接口的参数信息
     * @param information
     * @return
     */
    @PostMapping("/UserToGetOneInterfaceParams")
    BaseResponse UserToGetOneInterfaceParams(@RequestBody RequestApiInformation information){
        // 检查 userName
        if (information.getUserName() == null || information.getInterfacename() ==null)return ResultUtils.Error(null);

        // 提交给服务层
        final List<ApiInterfaceParams> oneParams = service.getOneParams(information.getInterfacename());
        // 检查服务层数据
        if (oneParams == null)return ResultUtils.Error(null);
        // 返回
        return ResultUtils.Success(oneParams);
    }



}
