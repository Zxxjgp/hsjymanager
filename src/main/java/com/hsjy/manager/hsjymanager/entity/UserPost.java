package com.hsjy.manager.hsjymanager.entity;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * 用户和岗位关联 sys_user_post
 * 
 * @author ruoyi
 */
public class UserPost extends Model<UserPost>
{
    /** 用户ID */
    private String userId;
    /** 岗位ID */
    private String postId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "UserPost{" +
                "userId='" + userId + '\'' +
                ", postId='" + postId + '\'' +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return this.postId;
    }
}
