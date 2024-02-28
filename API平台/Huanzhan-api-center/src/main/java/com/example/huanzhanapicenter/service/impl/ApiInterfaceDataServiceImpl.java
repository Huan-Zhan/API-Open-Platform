package com.example.huanzhanapicenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huanzhanapicenter.domain.ApiInterfaceData;
import com.example.huanzhanapicenter.service.ApiInterfaceDataService;
import com.example.huanzhanapicenter.mapper.ApiInterfaceDataMapper;
import org.springframework.stereotype.Service;

/**
* @author HZ
* @description 针对表【api_interface_data】的数据库操作Service实现
* @createDate 2023-10-28 15:30:39
*/
@Service
public class ApiInterfaceDataServiceImpl extends ServiceImpl<ApiInterfaceDataMapper, ApiInterfaceData>
    implements ApiInterfaceDataService{

    @Override
    public String getOneData(Long index) {

        if (index < 1) return  null ;

        final ApiInterfaceData byId = this.getById(index);
        if (byId == null) return null ;
        byId.getInterfacedata();

        return byId.getInterfacedata();
    }
}




