package com.hsjy.manager.hsjymanager.service;

import com.hsjy.manager.hsjymanager.entity.QueryUser;
import com.hsjy.manager.hsjymanager.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insertUser(User user);
    /**
     * 分页查询所有的用户
     * @param queryUser
     * @return
     */
    List<User> findAllUserList(QueryUser queryUser);
}