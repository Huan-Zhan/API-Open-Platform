package com.example.huanzhanapicenter.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.huanzhanapicenter.domain.*;
import com.example.huanzhanapicenter.utils.Tools;
import com.example.huanzhanapicenter.common.BaseCode;

import com.example.huanzhanapicenter.common.BaseMessage;
import com.example.huanzhanapicenter.service.ApicenterService;
import com.example.huanzhanapicenter.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 针对于 user 信息的管理
 * 方法的实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    ApicenterService service ;


    /**
     * 检查用户 是否存在 且 合法
     * @param user 用户对象的 信息
     * @return 封装好的 响应用户对象 ResponseUser
     */
    @Override
    public ResponseUser CheckUserIsExist(LoginUser user) {
        System.out.println("here is Login");
        // 1.检查 user 是否 为空
        if (user == null){
            return null;
        }

        // 2.检索用户是否存在
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("UserName",user.getUserName());
        final Apicenter checkuser = service.getOne(wrapper);
        if (checkuser == null){
            return null;
        }
        // 检查用户是否合法
        final String code = Tools.UserExist(checkuser);

        // 判断
        if (code.equals(BaseCode.Code_Error)) return null ;

        checkuser.setLastdate(Tools.AcquireTodaySeconds());
        service.update(checkuser,wrapper);

        return new ResponseUser(checkuser);
    }

    /**
     * 自动登录
     * @param userName 用户名
     * @return
     */
    @Override
    public ResponseUser AutoLogin(LoginUser userName) {
        System.out.println("here is AutoLogin "+userName.getUserName() );
        // 1.检测 userName 是否为空
        if(userName ==null) return null ;
        // 2.在数据库中检索 userName 返回对象
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("UserName",userName.getUserName());
        Apicenter datauser = service.getOne(wrapper);
        // 3.判断数据库中是否存在
        if (datauser == null) return null ;

        // 4. 装配返回值
        ResponseUser responseUser = new ResponseUser(datauser);

        //4.更新登录时间
        datauser.setLastdate(Tools.AcquireTodaySeconds());
        service.update(datauser,wrapper);

        // 4.返回需要的response
        return responseUser ;
    }


    /**
     * 用户注册方法
     * @param user 注册用户信息
     * @return
     */
    @Override
    public ResponseUser UserRegister(RegisterUser user) {


        // 1.检查密码 和 二次密码是否相同
        if (!user.getCheckPassword().equals(user.getUserPassword())) return null ;

        // 2.检查账户长度
        if (user.getUserName().length() > 30 || user.getUserName().length() <4) return null;
        // 3.检查密码长度
        if (user.getUserPassword().length() > 30 || user.getUserPassword().length() <6) return null;
        // 4.检查账号和密码 字符的合法性

        if (!Tools.CharIsPassing(user.getUserName()) || !Tools.CharIsPassing(user.getUserPassword())) return null;



        // 5.检查用户名是否重复
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("UserName",user.getUserName());
        if (service.count(wrapper) != 0) return null ;

        // 6.检查 邀请码是否存在 且合法 todo 上线的时候去数据库中查出邀请码
        if (!user.getInvitationCode().equals(BaseMessage.Invitation_Code)) return null ;

        // 7.一切合法 之后 开始存储数据

        Apicenter safeUser = new Apicenter();
        safeUser.setUsername(user.getUserName());
        safeUser.setUserpassword(user.getUserPassword());
        // 8.调用方法生成 ak ,sk 分配给用户
        safeUser.setAccesskey(Tools.CreatAccessKey(user.getUserName()));
        safeUser.setSecretkey(Tools.CreatSecrtKey(user.getUserPassword()));
        // 9.算法实现当前时间点
        safeUser.setLastdate(Tools.AcquireTodaySeconds());
        // 10.算法实现当前 随机数字
        safeUser.setNonenumber(RandomUtil.randomInt(10000,99999));

        // 11.向数据库钟存储数据
        final boolean save = service.save(safeUser);
        if (save) return new ResponseUser(safeUser);
        else return null ;
    }

    /**
     * 给管理员返回用户信息
     * @param user
     * @return
     */
    @Override
    public List<ResponseUser> AdminToGetUserInformation(LoginUser user) {
        // 1. 判断 接收的数据是否为空
        if (user == null) return  null ;
        // 2. 检测是否为管理员 todo 后期在数据库中查询
        if (!user.getUserName().equals("huanzhan")&&!user.getUserName().equals("admin")){
            return null ;
        }
        // 3. 获取数据库中的全部信息
        List<Apicenter> list = service.list();
        // 4. 批量封装入 ResponseUser 中
        List<ResponseUser> information = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIsdelete() != 1) information.add(new ResponseUser(list.get(i)));
        }
        // 6. 声明函数被前端调用
        System.out.println("here is AdminToGetUserInformation " + " user : " + user.getUserName());
        // 7. 返回


        return information;
    }

    @Override
    public ResponseUser AdminToDeleteUser(AdminUser user ) {
        // 1. 判断 接收的数据是否为空
        if (user == null ) return  null;
        // 2. 检测是否为管理员 todo 后期在数据库中查询
        if (!user.getAdminName().equals("huanzhan")&&!user.getAdminName().equals("admin")){
            return null ;
        }
        // 3. 删除该用户 实际是 将其 IsDelete 值设置为 1 ， 先查询到该用户 ，再修改他的 IsDelete
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("UserName",user.getUserName());
        Apicenter apicenter = service.getOne(wrapper);
        // 4. 判断该用户是否存在
        if (apicenter == null) return null ;
        // 5. 修改 该用户的 IsDelete 值
        apicenter.setIsdelete(1);
        service.update(apicenter,wrapper);

        // 6. 声明函数被前端调用
        System.out.println("here is AdminToGetUserInformation " + " user : " + user.getAdminName());
        // 7. 返回

        return new ResponseUser(apicenter);
    }

    @Override
    public ResponseUser AdminToUpdate(AdminUser user) {
        // 1. 判断 接收的数据是否为空
        if (user == null ) return  null;
        // 2. 检测是否为管理员 todo 后期在数据库中查询
        if (!user.getAdminName().equals("huanzhan")&&!user.getAdminName().equals("admin")){
            return null ;
        }
        // 3. 查询该用户，之后再更新
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("UserName",user.getUserName());
        Apicenter apicenter = service.getOne(wrapper);
        // 4. 判断该用户是否存在
        if (apicenter == null) return null ;
        // 5. 修改该用户的 前端修改项
        if (user.getGender().equals("男")){
            apicenter.setGender(1);
        }else {
            apicenter.setGender(0);
        }
        apicenter.setPhone(user.getPhone());
        apicenter.setNonenumber(user.getNoneNumber());

        service.update(apicenter,wrapper);

        // 6. 声明函数被前端调用
        System.out.println("here is AdminToGetUserInformation " + " user : " + user.getAdminName());
        // 7. 返回

        return new ResponseUser(apicenter);
    }

    @Override
    public Apicenter getOneUserInformation(String userName) {

        // 判断 userName 是否为空
        if (userName == null )return null;
        // 查询用户
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("UserName",userName);
        Apicenter one = service.getOne(wrapper);
        // 判断用户状态
        if (one == null || one.getIsdelete() == 1) return null;
        // 正常且存在 则返回

        return one;
    }

    /**
     * 仅限内部调用
     * @param userName
     * @return
     */
    @Override
    public boolean CheckUserStatus(String userName) {

        // 1.检查 userName 的值
        if (userName == null)return false;

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("UserName",userName);
        final Apicenter one = service.getOne(wrapper);

        if (one.getIsdelete() != 0)return false;


        return true;
    }
}
