package com.hsjy.manager.hsjymanager.dao;

import com.hsjy.manager.hsjymanager.entity.QueryUser;
import com.hsjy.manager.hsjymanager.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager.dao
 * @ClassName: UserDao
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/6 10:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/6 10:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Mapper
public interface UserDao {
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
