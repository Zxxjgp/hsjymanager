package com.hsjy.manager.hsjymanager.entity;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * 角色和菜单关联 sys_role_menu
 * 
 * @author jgp
 */
public class RoleMenu extends Model<RoleMenu>
{
    /** 角色ID */
    private String roleId;
    /** 菜单ID */
    private String menuId;

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    public String getMenuId()
    {
        return menuId;
    }

    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }

    @Override
    public String toString()
    {
        return "RoleMenu [roleId=" + roleId + ", menuId=" + menuId + "]";
    }

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }
}
