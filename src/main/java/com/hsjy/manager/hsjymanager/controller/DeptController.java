package com.hsjy.manager.hsjymanager.controller;

import com.hsjy.manager.hsjymanager.entity.Dept;
import com.hsjy.manager.hsjymanager.service.DeptService;
import com.hsjy.manager.hsjymanager.utils.constant.CodeConstants;
import com.hsjy.manager.hsjymanager.utils.exception.AuthException;
import com.hsjy.manager.hsjymanager.utils.result.Result;
import com.hsjy.manager.hsjymanager.utils.result.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager.controller
 * @ClassName: DeptController
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/6 21:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/6 21:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ResultGenerator.genSuccessResult
 */
@Controller
@RequestMapping("/dept")
public class DeptController {
    @Resource
    private DeptService deptService;

    @PostMapping("insertDept")
    @ResponseBody
    public Result insertDept(@RequestBody Dept dept){
        try {
            return ResultGenerator.genSuccessResult(deptService.insertDept(dept));
        } catch ( Exception e ) {
           throw new AuthException("插入异常",CodeConstants.UPDATE_EXCEPTION);
        }
    }

    @GetMapping("getParasDeptList")
    @ResponseBody
    public Result getParasDeptList(Dept dept){
        return  ResultGenerator.genSuccessResult(deptService.selectDeptList(dept));

    }

    @PostMapping("updateDept")
    @ResponseBody
    public  Result updateDept(Dept dept){
        return ResultGenerator.genSuccessResult(deptService.updateDept(dept));

    }

    @GetMapping("findByIdDeptDetail")
    @ResponseBody
    public  Result findByIdDeptDetail(String deptId){
        try {
            return ResultGenerator.genSuccessResult(deptService.selectDeptById(deptId));
        } catch ( Exception e ) {
            throw new AuthException("查询异常",CodeConstants.FIND_EXCEPTION);
        }
    }
    @GetMapping("checkDeptExistUser")
    @ResponseBody
    public Result checkDeptExistUser(String deptId){
        try {
            return ResultGenerator.genSuccessResult(deptService.checkDeptExistUser(deptId));
        } catch ( Exception e ) {
            throw new AuthException("查询异常",CodeConstants.FIND_EXCEPTION);
        }
    }
}
