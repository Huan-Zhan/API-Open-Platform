package com.example.huanzhanapicenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.huanzhanapicenter.domain.ApiInterfaceInformation;
import com.example.huanzhanapicenter.domain.ApiInterfaceParams;
import com.example.huanzhanapicenter.domain.ResponseApiInformation;
import com.example.huanzhanapicenter.service.ApiInterfaceParamsService;
import com.example.huanzhanapicenter.service.ApiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 针对于 api 接口信息的管理
 * 方法的实现类
 */
@Service
public class ApiServiceImpl implements ApiService {

    // api 接口信息库 service
    @Resource
    ApiInterfaceInformationServiceImpl informationService;

    @Resource
    ApiInterfaceParamsService paramsService;

    /**
     * 获取全部接口的全部信息
     * @return 全部接口的信息列表
     */
    public List<ResponseApiInformation> getInformation(String userName){
        // 1. 判断 接收的数据是否为空
        if (userName == null ) return  null;
        // 2. 检测是否为管理员 todo 后期在数据库中查询
        if (!userName.equals("huanzhan")&&!userName.equals("admin")){
            return null ;
        }
        // 3.查询数据
        List<ApiInterfaceInformation> list = informationService.list();
        // 4.判断查询的数据
        if (list == null)return null ;
        List<ResponseApiInformation> responseApiInformationList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            responseApiInformationList.add(new ResponseApiInformation(list.get(i)));
        }

        return responseApiInformationList ;
    }

    public List<ResponseApiInformation> userGetInformation(String userName){
        // 1. 判断 接收的数据是否为空
        if (userName == null ) return  null;
        // 2. 检测是否为管理员
//        if (!userName.equals("huanzhan")&&!userName.equals("admin")){
//            return null ;
//        }
        // 3.查询数据
        List<ApiInterfaceInformation> list = informationService.list();
        // 4.判断查询的数据
        if (list == null)return null ;
        List<ResponseApiInformation> responseApiInformationList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            responseApiInformationList.add(new ResponseApiInformation(list.get(i)));
        }

        return responseApiInformationList ;
    }


    /**
     * 储存一个接口的信息
     * @return 是否存储成功
     */
    public boolean addOneInformation(ApiInterfaceInformation information){

        // 判断 information 的 接口名字 是否存在 ，不存则 拒绝存储
        if (information.getInterfacename()==null)return false;
        if (information.getMethod() == null)return false;
        if (information.getInterfacefunction()==null)return false;
        if (information.getInterfacestatus()==null)return false;
        if (information.getOnlineaddress() ==null)return false;
        if (information.getRequestparams() ==null)return false;
        if (information.getRespondtype() ==null)return false;
        if (information.getResponddata()==null)return false;
        if (information.getInvokednumber()==null)return false;

        final boolean save = informationService.save(information);

        return save ;
    }

    /**
     * api 接口信息跟新（任意数据的更新）
     * @param information 需要更新的数据
     * @return 是否更新成功
     */
    public boolean updateOneInformation(ApiInterfaceInformation information){
        // 判断 information 的 接口名字 是否存在 ，不存则 拒绝存储
        if (information.getInterfacename()==null)return false;

        // 1.查询该接口是否存在
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("InterfaceName",information.getInterfacename());
        ApiInterfaceInformation one = informationService.getOne(wrapper);

        // 2.如果存在 则 将information未改变的内容(空内容) 填充为旧内容
        if (one == null)return false;

        // 设置 接口方法
        if (information.getMethod()==null)information.setMethod(one.getMethod());
        // 设置 接口功能
        if (information.getInterfacefunction()==null)information.setInterfacefunction(one.getInterfacefunction());
        // 设置 接口状态
        if (information.getInterfacestatus()==null)information.setInterfacestatus(one.getInterfacestatus());
        // 设置 接口地址
        if (information.getOnlineaddress()==null)information.setOnlineaddress(one.getOnlineaddress());
        // 设置 请求参数
        if (information.getRequestparams()==null)information.setRequestparams(one.getRequestparams());
        // 设置 返回数据类型
        if (information.getRespondtype()==null)information.setRespondtype(one.getRespondtype());
        // 设置 返回数据说明
        if (information.getResponddata()==null)information.setResponddata(one.getResponddata());
        // 设置 被调用次数
        information.setInvokednumber(one.getInvokednumber());

        // 3.更新数据,并返回结果

        final boolean update = informationService.update(information, wrapper);

        return update ;
    }

    /**
     * 接口被调用次数加一
     * @param apiInterfaceName 接口名字
     * @return 接口次数加一 是否成功
     */
    public boolean ApiAddInvokedNumber(String apiInterfaceName){
        // 检查接口名字是否存在
        if (apiInterfaceName ==null)return false ;

        // 2.查询该接口是否存在
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("InterfaceName",apiInterfaceName);
        ApiInterfaceInformation one = informationService.getOne(wrapper);
        if (one == null) return false;

        // 3.更新数据
        one.setInvokednumber(one.getInvokednumber()+1);
        boolean update = informationService.update(one, wrapper);

        // 4.返回是否更新成功
        return update ;
    }

    /**
     * 根据 接口名字获取 接口信息
     * @param apiInterfaceName 接口名字
     * @return 返回接口信息
     */
    @Override
    public ApiInterfaceInformation getOneInformation(String apiInterfaceName) {
        // 1.判断 interfaceName 是否为空
        if (apiInterfaceName ==null) return null ;
        // 2.查询 该接口信息
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("InterfaceName",apiInterfaceName);
        ApiInterfaceInformation one = informationService.getOne(wrapper);
        // 3.如果该接口不存在，则返回空 ，
        if (one == null)return null;
        // 4.返回全部数据

        return one;
    }

    @Override
    public List<ApiInterfaceParams> getOneParams(String apiInterfaceName) {

        if (apiInterfaceName == null)return null;

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("InterfaceName",apiInterfaceName);
        final List list = paramsService.list(wrapper);

        if (list == null)return null ;


        return list;
    }

    /**
     * 检查接口状态
     * @param apiInterfaceName
     * @return
     */
    @Override
    public boolean CheckInterfaceStatus(String apiInterfaceName) {

        if (apiInterfaceName == null) return false;
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("InterfaceName",apiInterfaceName);
        ApiInterfaceInformation one = informationService.getOne(wrapper);

        if (one == null || one.getInterfacestatus()!=0)return  false ;


        return true;
    }


}
