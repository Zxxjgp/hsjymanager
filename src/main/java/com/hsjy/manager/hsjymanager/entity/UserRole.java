package com.hsjy.manager.hsjymanager.entity;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * 用户和角色关联 sys_user_role
 * 
 * @author ruoyi
 */
public class UserRole extends Model<UserRole>
{
    /** 用户ID */
    private String userId;
    /** 角色ID */
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }
}
