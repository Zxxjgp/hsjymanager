package com.hsjy.manager.hsjymanager.controller;

import com.hsjy.manager.hsjymanager.entity.QueryUser;
import com.hsjy.manager.hsjymanager.entity.User;
import com.hsjy.manager.hsjymanager.service.UserService;
import com.hsjy.manager.hsjymanager.utils.page.Page;
import com.hsjy.manager.hsjymanager.utils.result.Result;
import com.hsjy.manager.hsjymanager.utils.result.ResultGenerator;
import org.springframework.stereotype.Controller;
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
    @Resource
    private UserService userService;

    /**
     * 插入新的用户
     * @return
     */
    @PostMapping("/insertUser")
    public Result insertUser(@RequestBody User user){
        return ResultGenerator.genSuccessResult(userService.insertUser(user));
    }
    /**
     * 分页查询所有的用户
     */
    @GetMapping("getAllUserList")
    @ResponseBody
    public Page<User> getAllUserList(Page page, QueryUser queryUser){
        return userService.findAllUserList(page,queryUser);
    }
}
