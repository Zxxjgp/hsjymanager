package com.hsjy.manager.hsjymanager.service.impl;

import com.hsjy.manager.hsjymanager.dao.UserDao;
import com.hsjy.manager.hsjymanager.entity.QueryUser;
import com.hsjy.manager.hsjymanager.entity.User;
import com.hsjy.manager.hsjymanager.service.UserService;
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
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public List<User> findAllUserList(QueryUser queryUser) {
        return userDao.findAllUserList(queryUser);
    }
}
