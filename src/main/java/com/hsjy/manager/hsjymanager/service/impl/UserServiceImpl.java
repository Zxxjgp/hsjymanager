package com.hsjy.manager.hsjymanager.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsjy.manager.hsjymanager.dao.*;
import com.hsjy.manager.hsjymanager.entity.*;
import com.hsjy.manager.hsjymanager.service.DeptService;
import com.hsjy.manager.hsjymanager.service.RoleService;
import com.hsjy.manager.hsjymanager.service.UserService;
import com.hsjy.manager.hsjymanager.utils.Convert;
import com.hsjy.manager.hsjymanager.utils.MD5Password;
import com.hsjy.manager.hsjymanager.utils.StringUtils;
import com.hsjy.manager.hsjymanager.utils.constant.CodeConstants;
import com.hsjy.manager.hsjymanager.utils.constant.UserConstants;
import com.hsjy.manager.hsjymanager.utils.exception.AuthException;
import com.hsjy.manager.hsjymanager.utils.page.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleDao roleService;

    @Resource
    private PostDao postDao;

    @Resource
    private UserPostDao userPostDao;

    @Override

    public Page<User> selectUserList(Page page, User user) {
        List<User> list = baseMapper.selectUserList(page, user);
        page.setCount(page.getTotal());
        page.setList(list);
        return page;
    }

    @Override
    public User selectUserByLoginName(String userName) {
        return baseMapper.selectUserByLoginName(userName);
    }

    @Override
    public User selectUserByPhoneNumber(String phoneNumber) {
        return baseMapper.selectUserByPhoneNumber(phoneNumber);
    }

    @Override
    public User selectUserByEmail(String email) {
        return baseMapper.selectUserByEmail(email);
    }

    @Override
    public User selectUserById(String userId) {
        return baseMapper.selectUserById(userId);
    }

    @Override
    public int deleteUserById(String userId) {
        try {
            userRoleMapper.deleteUserRoleByUserId(userId);
            userPostDao.deleteUserPostByUserId(userId);
        } catch ( Exception e ) {
            throw new AuthException("删除关联哦用户失败",CodeConstants.DELETE_EXCEPTION);
        }
        return baseMapper.deleteUserById(userId);
    }

    @Override
    public int deleteUserByIds(String ids) throws Exception {
        String[] userIds = Convert.toStrArray(ids);
        for (String userId : userIds) {
            if (User.isAdmin(userId)){
                throw new AuthException("不允许删除超级管理员用户",CodeConstants.DELETE_EXCEPTION);
            }
        }
        return baseMapper.deleteUserByIds(userIds);
    }

    @Override
    public int insertUser(User user) {
        user.setSalt(user.getUserName());
        user.setPassword(MD5Password.generatePassword(user.getPassword(),user.getUserName()));
        user.setCreateBy("获取登陆的用户信息");
        int rows = 0;
        try {
            rows = baseMapper.insertUser(user);
        } catch ( Exception e ) {
            throw new AuthException("插入用户失败", CodeConstants.INSERT_EXCEPTION);
        }
        //与用户进行关联信息
        insertUserRole(user);
        return rows;
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(User user)
    {
        // 新增用户与角色管理
        List<UserRole> list = new ArrayList<UserRole>();
        for (String roleId : user.getRoleIds())
        {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0)
        {
            userRoleMapper.batchUserRole(list);
        }
    }

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return
     */
    @Override
    public int updateUser(User user) {
        String userId = user.getUserId();
        user.setUpdateBy("得到登录名称");
        userRoleMapper.deleteUserRoleByUserId(userId);
        insertUserRole(user);
        userPostDao.deleteUserPostByUserId(userId);
        insertUserPost(user);
        return baseMapper.updateUser(user);
    }

    /**
     * 修改用户个人详细信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(User user) {
        return baseMapper.updateUser(user);
    }

    /**
     * 修改用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetUserPwd(User user) {
        user.setPassword(MD5Password.generatePassword(user.getPassword(),user.getUserName()));
        return updateUserInfo(user);
    }
    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 用户名
     * @return
     */
    @Override
    public String checkLoginNameUnique(String loginName) {
        int count = baseMapper.checkLoginNameUnique(loginName);
        if (count > 0)
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户名
     * @return
     */
    @Override
    public String checkPhoneUnique(User user) {
        String userId = StringUtils.isNull(user.getUserId()) ? String.valueOf(-1L) : user.getUserId();
        User info = baseMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId() != userId)
        {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user
     * @return
     */
    @Override
    public String checkEmailUnique(User user) {
        String userId = StringUtils.isNull(user.getUserId()) ? String.valueOf(-1L) : user.getUserId();
        User info = baseMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId() != userId)
        {
            return UserConstants.USER_EMAIL_NOT_UNIQUE;
        }
        return UserConstants.USER_EMAIL_UNIQUE;
    }

    /**
     * 查询用户所属角色组
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(String userId) {
        List<Role> list = roleService.selectRolesByUserId(userId);
        StringBuilder stringBuilder = new StringBuilder();
        for ( Role role : list) {
            stringBuilder.append(role.getRoleName().split(","));
        }
        if (StringUtils.isNotEmpty(stringBuilder.toString()))
        {
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    @Override
    public String selectUserPostGroup(String userId) {
        List<Post> list = postDao.selectPostsByUserId(userId);
        StringBuffer idsStr = new StringBuffer();
        for (Post post : list)
        {
            idsStr.append(post.getPostName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 新增用户岗位信息
     *
     * @param user 用户对象
     */
    public void insertUserPost(User user)
    {
        // 新增用户与岗位管理
        List<UserPost> list = new ArrayList<UserPost>();
        for (String postId : user.getPostIds())
        {
            UserPost up = new UserPost();
            up.setUserId(user.getUserId());
            up.setPostId(postId);
            list.add(up);
        }
        if (list.size() > 0)
        {
            userPostDao.batchUserPost(list);
        }
    }
}
