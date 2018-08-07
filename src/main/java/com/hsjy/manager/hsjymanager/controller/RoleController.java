package com.hsjy.manager.hsjymanager.controller;

import com.hsjy.manager.hsjymanager.entity.Role;
import com.hsjy.manager.hsjymanager.service.RoleService;
import com.hsjy.manager.hsjymanager.utils.constant.CodeConstants;
import com.hsjy.manager.hsjymanager.utils.exception.AuthException;
import com.hsjy.manager.hsjymanager.utils.page.Page;
import com.hsjy.manager.hsjymanager.utils.result.Result;
import com.hsjy.manager.hsjymanager.utils.result.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager.controller
 * @ClassName: RoleController
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/7 9:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/7 9:07
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;


    /**\
     * 未通过检测
     * @param role
     * @return
     */
    @GetMapping("findRoleList")
    public List<Role> findRoleList(Role role){
       return roleService.selectRoleList(role);
    }

    /**
     * 角色列表
     * @param userId
     * @return
     */
    @GetMapping("finfByUserIdRoleList")
    public Result finfByUserIdRoleList(String userId){
        try {
            return ResultGenerator.genSuccessResult(roleService.selectRoleKeys(userId));
        } catch ( Exception e ) {
            throw new AuthException("查询异常",CodeConstants.FIND_EXCEPTION);
        }
    }

    /**
     * 通过测试
     * @param userId
     * @return
     */
    @GetMapping("findRolesByUserId")
    public Result findRolesByUserId( String userId){
        return ResultGenerator.genSuccessResult(roleService.selectRolesByUserId( userId));
    }

    /**
     * 查询所有通过测试
     * @param page
     * @return
     */
    @GetMapping("findRoleAll")
    public Result findRoleAll(Page page){
        return ResultGenerator.genSuccessResult(roleService.selectRoleAll());
    }
    //通过测试
    @GetMapping("findRoleById")
    public Result selectRoleById(String roleId){
        return ResultGenerator.genSuccessResult(roleService.selectRoleById(roleId));
    }

    @GetMapping("deleteRoleById")
    public Result deleteRoleById(String roleId){
        try {
            return ResultGenerator.genSuccessResult(roleService.deleteRoleById(roleId));
        } catch ( Exception e ) {
            throw new AuthException("删除异常",CodeConstants.DELETE_EXCEPTION);
        }
    }
    @GetMapping("deleteRoleByIds")
    public Result deleteRoleByIds(String ids) throws Exception {
        return  ResultGenerator.genSuccessResult(roleService.deleteRoleByIds(ids));
    }

    @PostMapping("insertRole")
    public  Result insertRole(Role role){
        try {
            return  ResultGenerator.genSuccessResult(roleService.insertRole(role));
        } catch ( Exception e ) {
            throw  new AuthException("插入失败",CodeConstants.INSERT_EXCEPTION);
        }
    }

    @PostMapping("updateRole")
    public Result  updateRole(Role role){
        try {
            return  ResultGenerator.genSuccessResult(roleService.updateRole(role));
        } catch ( Exception e ) {
            throw  new AuthException("更新失败",CodeConstants.INSERT_EXCEPTION);
        }
    }

    @GetMapping("checkRoleNameUnique")
    public  Result checkRoleNameUnique(Role role){
        try {
            return  ResultGenerator.genSuccessResult(roleService.checkRoleNameUnique(role));
        } catch ( Exception e ) {
            throw  new AuthException("查询失败",CodeConstants.FIND_EXCEPTION);
        }
    }
    @GetMapping("checkRoleKeyUnique")
    public  Result checkRoleKeyUnique(Role role){
        try {
            return  ResultGenerator.genSuccessResult(roleService.checkRoleKeyUnique(role));
        } catch ( Exception e ) {
            throw  new AuthException("查询失败",CodeConstants.FIND_EXCEPTION);
        }
    }
}
