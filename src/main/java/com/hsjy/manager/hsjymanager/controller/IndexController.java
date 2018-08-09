package com.hsjy.manager.hsjymanager.controller;

import com.hsjy.manager.hsjymanager.entity.Menu;
import com.hsjy.manager.hsjymanager.entity.User;
import com.hsjy.manager.hsjymanager.service.MenuService;
import com.hsjy.manager.hsjymanager.utils.ShiroUtils;
import com.hsjy.manager.hsjymanager.utils.result.Result;
import com.hsjy.manager.hsjymanager.utils.result.ResultGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

import static com.hsjy.manager.hsjymanager.utils.ShiroUtils.getUser;


/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class IndexController
{
    @Resource
    private MenuService menuService;

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
    @ResponseBody
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user =getUser();
        List<Menu> menus = menuService.selectMenusByUserId(user.getUserId());
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", "4646446");
        return "index";
    }

}
