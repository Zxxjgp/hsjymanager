package com.hsjy.manager.hsjymanager.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsjy.manager.hsjymanager.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户表 数据层
 * 
 * @author jgp
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole>
{

    /**
     * 通过用户ID删除用户和角色关联
     * 
     * @param userId 用户ID
     * @return 结果
     */
    int deleteUserRoleByUserId(String userId);

    /**
     * 批量删除用户和角色关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteUserRole(String[] ids);

    /**
     * 通过角色ID查询角色使用数量
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    int countUserRoleByRoleId(String roleId);

    /**
     * 批量新增用户角色信息
     * 
     * @param userRoleList 用户角色列表
     * @return 结果
     */
     int batchUserRole(List<UserRole> userRoleList);

}
