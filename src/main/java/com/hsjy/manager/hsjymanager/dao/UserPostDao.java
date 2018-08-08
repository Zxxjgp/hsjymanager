package com.hsjy.manager.hsjymanager.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsjy.manager.hsjymanager.entity.UserPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户与岗位 表 数据层
 * 
 * @author jgp
 */
@Mapper
public interface UserPostDao extends BaseMapper<UserPost>
{

    /**
     * 通过用户ID删除用户和岗位关联
     * 
     * @param userId 用户ID
     * @return 结果
     */
    int deleteUserPostByUserId(String userId);
    
    /**
     * 通过岗位ID查询岗位使用数量
     * 
     * @param postId 岗位ID
     * @return 结果
     */
    int countUserPostById(String postId);
    
    /**
     * 批量删除用户和岗位关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteUserPost(String[] ids);

    /**
     * 批量新增用户岗位信息
     * 
     * @param userPostList 用户角色列表
     * @return 结果
     */
    int batchUserPost(List<UserPost> userPostList);

}
