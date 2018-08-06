package com.hsjy.manager.hsjymanager.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsjy.manager.hsjymanager.dao.UserDao;
import com.hsjy.manager.hsjymanager.entity.QueryUser;
import com.hsjy.manager.hsjymanager.entity.User;
import com.hsjy.manager.hsjymanager.service.UserService;
import com.hsjy.manager.hsjymanager.utils.page.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager.service.impl
 * @ClassName: UserServiceImpl
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/6 10:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/6 10:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {
    @Override
    public User findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }

    @Override
    public int insertUser(User user) {
        return baseMapper.insertUser(user);
    }

    @Override
    public Page<User> findAllUserList(Page page, QueryUser queryUser){
        List<User> list = baseMapper.findAllUserList(queryUser);
        page.setCount(page.getTotal());
        page.setList(list);
        return page;
    }


}
