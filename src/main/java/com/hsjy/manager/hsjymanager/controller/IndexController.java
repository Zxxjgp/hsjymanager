package com.hsjy.manager.hsjymanager.controller;

import com.hsjy.manager.hsjymanager.entity.Test;
import com.hsjy.manager.hsjymanager.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {
    @Resource
    private TestService testService;
    @GetMapping("/list")
    public String index(){

        return "indexs";
    }
    @RequestMapping("login")
    @ResponseBody
    public List<Test> all(){
        return testService.findall();
    }

}
