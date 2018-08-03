package com.hsjy.manager.hsjymanager.controller;

import com.hsjy.manager.hsjymanager.entity.Test;
import com.hsjy.manager.hsjymanager.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("index")
public class IndexController {
    @Resource
    private TestService testService;
    @RequestMapping("list")
    public String index(){

        return "index";
    }
    @RequestMapping("all")
    @ResponseBody
    public List<Test> all(){
        return testService.findall();
    }

}
