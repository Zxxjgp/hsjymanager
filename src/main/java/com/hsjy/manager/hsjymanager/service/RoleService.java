package com.hsjy.manager.hsjymanager.service;

import com.hsjy.manager.hsjymanager.entity.Role;
import com.hsjy.manager.hsjymanager.utils.page.Page;

import java.util.List;
import java.util.Set;

/**
 * 角色业务层
 * 
 * @author jgp
 */
public interface RoleService
{

    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    List<Role> selectRoleList(Role role);

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectRoleKeys(String userId);

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    List<Role> selectRolesByUserId(String userId);

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
    List<Role> selectRoleAll();

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    Role selectRoleById(String roleId);

    /**
     * 通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    boolean deleteRoleById(String roleId);

    /**
     * 批量删除角色用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
     int deleteRoleByIds(String ids) throws Exception;

    /**
     * 新增保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    int insertRole(Role role);

    /**
     * 修改保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    int updateRole(Role role);

    /**
     * 校验角色名称是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
     String checkRoleNameUnique(Role role);
    
    /**
     * 校验角色权限是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
      String checkRoleKeyUnique(Role role);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    int countUserRoleByRoleId(Long roleId);
}
