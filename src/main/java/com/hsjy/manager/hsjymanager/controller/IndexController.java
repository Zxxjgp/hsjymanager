package com.hsjy.manager.hsjymanager.controller;

import com.hsjy.manager.hsjymanager.entity.Menu;
import com.hsjy.manager.hsjymanager.entity.User;
import com.hsjy.manager.hsjymanager.realm.MyShiroRealm;
import com.hsjy.manager.hsjymanager.service.MenuService;
import com.hsjy.manager.hsjymanager.service.UserService;
import com.hsjy.manager.hsjymanager.utils.BeanUtils;
import com.hsjy.manager.hsjymanager.utils.ShiroUtils;
import com.hsjy.manager.hsjymanager.utils.constant.UserConstants;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class IndexController
{
    @Resource
    private UserService userService;
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
    @PostMapping("/doLogin")
    @ResponseBody
    public Result doLogin(HttpServletRequest reques, HttpSession session, String username, String password) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            token.setUsername(username);
            token.setPassword(MyShiroRealm.getPassword(username,password).toCharArray());
        } catch (AuthenticationException e) {
            token.clear();
            return ResultGenerator.genFailResult("登录失败，用户名或密码错误！");
        }
        User user = userService.selectUserByLoginName(username);

        session.setMaxInactiveInterval(1800000);
        session.setAttribute("key",user);
        Map<String,String> map = new HashMap<>(16);
        map.put("code",UserConstants.MENU_NAME_UNIQUE);
        return ResultGenerator.genSuccessResult(map);
    }

    @GetMapping("/index")

    public String index( HttpSession session, ModelMap mmap)
    {
        // 取身份信息
        User user = (User) session.getAttribute("key");

        List<Menu> menus = menuService.selectMenusByUserId(user.getUserId());
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", "4646446");
        return "index";
    }
    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", "2001-08-05");
        return "main";
    }

}
