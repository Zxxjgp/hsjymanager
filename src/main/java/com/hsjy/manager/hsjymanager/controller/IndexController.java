package com.hsjy.manager.hsjymanager.controller;

import com.hsjy.manager.hsjymanager.utils.result.Result;
import com.hsjy.manager.hsjymanager.utils.result.ResultGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class IndexController
{

    @GetMapping("/")
    public String index()
    {
        return "login";
    }

    @GetMapping("/helloworld")
    @ResponseBody
    public Result helloWorld() {
        return ResultGenerator.genSuccessResult("helloworld");
    }
    @GetMapping("/doLogin")
    @ResponseBody
    public Result doLogin(String username, String password) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            token.clear();
            return ResultGenerator.genFailResult("登录失败，用户名或密码错误！");
        }
        return ResultGenerator.genSuccessResult("登录成功");
    }

    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
     /*   // 取身份信息
        User user = getUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUserId(user.getUserId());
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", ruoYiConfig.getCopyrightYear());*/
        return "index";
    }

}
