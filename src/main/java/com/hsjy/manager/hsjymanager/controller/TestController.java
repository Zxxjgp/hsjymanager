package com.hsjy.manager.hsjymanager.controller;

import com.hsjy.manager.hsjymanager.entity.Test;
import com.hsjy.manager.hsjymanager.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager.controller
 * @ClassName: TestController
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/9/3 20:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/9/3 20:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Resource
    private TestService testService;

    @RequestMapping("index")
    public List<Test> get(){
        return testService.findall();
    }

}
