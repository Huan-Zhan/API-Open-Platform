package com.example.huanzhanapicenter.service;


import com.example.huanzhanapicenter.domain.ApiInterfaceInformation;
import com.example.huanzhanapicenter.domain.ApiInterfaceParams;
import com.example.huanzhanapicenter.domain.ResponseApiInformation;

import java.util.List;

/**
 * 针对于 api 接口信息的管理
 * 方法的实现类
 */
public interface ApiService {

    List<ResponseApiInformation> getInformation(String userName);
    List<ResponseApiInformation> userGetInformation(String userName);
    boolean addOneInformation(ApiInterfaceInformation information);
    boolean updateOneInformation(ApiInterfaceInformation information);
    boolean ApiAddInvokedNumber(String apiInterfaceName);

    ApiInterfaceInformation getOneInformation(String apiInterfaceName);
    List<ApiInterfaceParams> getOneParams(String apiInterfaceName);
    boolean CheckInterfaceStatus(String apiInterfaceName);

}
