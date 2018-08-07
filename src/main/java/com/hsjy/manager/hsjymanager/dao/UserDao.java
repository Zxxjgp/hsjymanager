package com.hsjy.manager.hsjymanager.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsjy.manager.hsjymanager.entity.User;
import com.hsjy.manager.hsjymanager.utils.page.Page;
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
public interface UserDao extends BaseMapper<User> {
    /**
     * 根据用户名查找
     * @param username
     * @return
     */
   /*  User findByUsername(String username);
    *//**
     * 插入用户
     * @param user
     * @return
     *//*
    int insertUser(User user);

    *//**
     * 分页查询所有的用户
     * @param queryUser
     * @return
     *//*
  *//*  @Select("select * from user_sys")*//*
    List<User> findAllUserList(QueryUser queryUser);*/

    /**
     * 根据条件分页查询用户对象
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<User> selectUserList(Page page, User user);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    User selectUserByLoginName(String userName);

    /**
     * 通过手机号码查询用户
     *
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    User selectUserByPhoneNumber(String phoneNumber);

    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    User selectUserByEmail(String email);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
     User selectUserById(String userId);

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    int deleteUserById(String userId);

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteUserByIds(String[] ids);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(User user);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int insertUser(User user);

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    int checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
     User checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    User checkEmailUnique(String email);

}
