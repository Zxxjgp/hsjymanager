package com.hsjy.manager.hsjymanager.controller;

import com.hsjy.manager.hsjymanager.entity.Dept;
import com.hsjy.manager.hsjymanager.entity.QueryUser;
import com.hsjy.manager.hsjymanager.entity.User;
import com.hsjy.manager.hsjymanager.service.DeptService;
import com.hsjy.manager.hsjymanager.service.RoleService;
import com.hsjy.manager.hsjymanager.service.UserService;
import com.hsjy.manager.hsjymanager.utils.StringUtils;
import com.hsjy.manager.hsjymanager.utils.constant.CodeConstants;
import com.hsjy.manager.hsjymanager.utils.constant.UserConstants;
import com.hsjy.manager.hsjymanager.utils.exception.AuthException;
import com.hsjy.manager.hsjymanager.utils.page.Page;
import com.hsjy.manager.hsjymanager.utils.result.Result;
import com.hsjy.manager.hsjymanager.utils.result.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager.controller
 * @ClassName: UserController
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/6 10:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/6 10:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private String prefix = "user";
    @Resource
    private DeptService deptService;

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

  /*  *//**
     * 插入新的用户
     * @return
     *//*
    @PostMapping("/insertUser")
    public Result insertUser(@RequestBody User user){
        return ResultGenerator.genSuccessResult(userService.insertUser(user));
    }
    *//**
     * 分页查询所有的用户
     *//*
    @GetMapping("getAllUserList")
    @ResponseBody
    public Page<User> getAllUserList(Page page, QueryUser queryUser){
        return userService.findAllUserList(page,queryUser);
    }*/

    @GetMapping("listUser")
    @ResponseBody
    @Transactional
    public Page<User> getListUser(Page page, User user){
        return userService.selectUserList(page, user);
    }
    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap, Dept dept)
    {
        mmap.put("roles", roleService.selectRoleAll());
        mmap.put("dept",deptService.selectDeptList(dept));
        return prefix + "/add";
    }

    /**
     * 插入用户信息
     * @param user
     * @return
     */
    @PostMapping("insertUser")
    @ResponseBody
    public  Result insertUser(User user){
        if (StringUtils.isNotNull(user.getUserId()) && User.isAdmin(user.getUserId()))
        {
            throw new AuthException("不允许修改超级管理员用户",UserConstants.SUPER_ADMINISTRATOR);
        }
        return ResultGenerator.genSuccessResult(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") String userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", roleService.selectRolesByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 保存修改过后的用户
     */
    @PostMapping("editUser")
    @ResponseBody
    public Result editSave(User user)
    {
        if (StringUtils.isNotNull(user.getUserId()) && User.isAdmin(user.getUserId()))
        {
            throw new AuthException("不允许修改超级管理员用户",UserConstants.SUPER_ADMINISTRATOR);
        }
        return ResultGenerator.genSuccessResult(userService.updateUser(user));
    }

    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") String userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }
    @PostMapping("/resetPassword")
    @ResponseBody
    public Result resetPwd(User user)
    {
        return ResultGenerator.genSuccessResult(userService.resetUserPwd(user));
    }

    @PostMapping("/remove")
    @ResponseBody
    public Result remove(String ids)
    {
        try
        {
            return ResultGenerator.genSuccessResult(userService.deleteUserByIds(ids));
        }
        catch (Exception e)
        {
           throw new AuthException("删除用户组",CodeConstants.DELETE_EXCEPTION);
        }
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(User user)
    {
        String uniqueFlag = "0";
        if (StringUtils.isNotNull(user))
        {
            uniqueFlag = userService.checkLoginNameUnique(user.getLoginName());
        }
        return uniqueFlag;
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(User user)
    {
        String uniqueFlag = "0";
        if (StringUtils.isNotNull(user))
        {
            uniqueFlag = userService.checkPhoneUnique(user);
        }
        return uniqueFlag;
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(User user)
    {
        String uniqueFlag = "0";
        if (StringUtils.isNotNull(user))
        {
            uniqueFlag = userService.checkEmailUnique(user);
        }
        return uniqueFlag;
    }
}
