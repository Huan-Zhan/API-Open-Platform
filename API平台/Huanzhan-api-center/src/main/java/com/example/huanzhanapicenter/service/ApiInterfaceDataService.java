package com.example.huanzhanapicenter.service;

import com.example.huanzhanapicenter.domain.ApiInterfaceData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author HZ
* @description 针对表【api_interface_data】的数据库操作Service
* @createDate 2023-10-28 15:30:39
*/
public interface ApiInterfaceDataService extends IService<ApiInterfaceData> {

    String getOneData(Long index);

}
