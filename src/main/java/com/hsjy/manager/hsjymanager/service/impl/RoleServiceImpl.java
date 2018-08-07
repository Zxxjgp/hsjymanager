package com.hsjy.manager.hsjymanager.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsjy.manager.hsjymanager.dao.RoleDao;
import com.hsjy.manager.hsjymanager.entity.Role;
import com.hsjy.manager.hsjymanager.service.RoleService;
import com.hsjy.manager.hsjymanager.utils.Convert;
import com.hsjy.manager.hsjymanager.utils.StringUtils;
import com.hsjy.manager.hsjymanager.utils.constant.UserConstants;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager.service.impl
 * @ClassName: RoleServiceImpl
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/6 22:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/6 22:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao,Role> implements RoleService {

    @Override
    public List<Role> selectRoleList(Role role) {
        List<Role> list = baseMapper.selectRoleList(role);
        return list;
    }

    @Override
    public Set<String> selectRoleKeys(String userId) {
        List<Role> perms = baseMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (Role perm : perms)
        {
            if (StringUtils.isNotNull(perms))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Role> selectRolesByUserId(String userId) {
        List<Role> userRoles = baseMapper.selectRolesByUserId(userId);
        List<Role> roles = baseMapper.selectRolesAll();
        for (Role role : roles)
        {
            for (Role userRole : userRoles)
            {
                if (role.getRoleId() == userRole.getRoleId())
                {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    @Override
    public List<Role> selectRoleAll() {
        return baseMapper.selectRolesAll();
    }

    @Override
    public Role selectRoleById(String roleId) {
        return baseMapper.selectRoleById(roleId);
    }

    @Override
    public boolean deleteRoleById(String roleId) {
        return baseMapper.deleteRoleById(roleId) > 0 ? true : false;
    }

    @Override
    public int deleteRoleByIds(String ids) throws Exception {
        String[] roleIds = Convert.toStrArray(ids);
        for (String roleId : roleIds)
        {
            Role role = selectRoleById(roleId);
/*            if (countUserRoleByRoleId(roleId) > 0)
            {
                throw new Exception(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }*/
        }
        return baseMapper.deleteRoleByIds(roleIds);
    }

    @Override
    public int insertRole(Role role) {
        role.setCreateBy("创建人");

        return baseMapper.insertRole(role);
    }

    @Override
    public int updateRole(Role role) {
        role.setCreateBy("次该人");
        return baseMapper.updateRole(role);
    }

    @Override
    public String checkRoleNameUnique(Role role) {
        String roleId = StringUtils.isNull(role.getRoleId()) ? String.valueOf(-1) : role.getRoleId();
        Role info = baseMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId() != roleId)
        {
            return UserConstants.ROLE_NAME_NOT_UNIQUE;
        }
        return UserConstants.ROLE_NAME_UNIQUE;
    }

    @Override
    public String checkRoleKeyUnique(Role role) {
        String roleId = StringUtils.isNull(role.getRoleId()) ? String.valueOf(-1L) : role.getRoleId();
        Role info = baseMapper.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId() != roleId)
        {
            return UserConstants.ROLE_KEY_NOT_UNIQUE;
        }
        return UserConstants.ROLE_KEY_UNIQUE;
    }

    @Override
    public int countUserRoleByRoleId(Long roleId) {
        return 0;
    }

}
