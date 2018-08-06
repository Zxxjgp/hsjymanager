package com.hsjy.manager.hsjymanager.service;

import com.hsjy.manager.hsjymanager.entity.QueryUser;
import com.hsjy.manager.hsjymanager.entity.User;
import com.hsjy.manager.hsjymanager.utils.page.Page;



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
    Page<User> findAllUserList(Page page, QueryUser queryUser);
}