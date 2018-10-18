package com.hsjy.manager.hsjymanager.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsjy.manager.hsjymanager.dao.MenuDao;
import com.hsjy.manager.hsjymanager.dao.RoleMenuDao;
import com.hsjy.manager.hsjymanager.entity.Menu;
import com.hsjy.manager.hsjymanager.entity.Role;
import com.hsjy.manager.hsjymanager.service.MenuService;
import com.hsjy.manager.hsjymanager.utils.StringUtils;
import com.hsjy.manager.hsjymanager.utils.TreeUtils;
import com.hsjy.manager.hsjymanager.utils.constant.UserConstants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager.service.impl
 * @ClassName: MenuServiceImpl
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/8 17:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/8 17:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao,Menu> implements MenuService {
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";
    @Resource
    private RoleMenuDao roleMenuDao;

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<Menu> selectMenusByUserId(String userId) {
        List<Menu> menus = baseMapper.selectMenusByUserId(userId);

        return TreeUtils.getChildPerms(menus,"0");
    }

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    @Override
    @Transactional
    public List<Menu> selectMenuList(Menu menu) {
        return baseMapper.selectMenuList(menu);
    }
    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    @Override
    public List<Menu> selectMenuAll() {
        return null;
    }
    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectPermsByUserId(String userId) {
        List<String> perms = baseMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    @Override
    public List<Map<String, Object>> roleMenuTreeData(Role role) {
        String roleId = role.getRoleId();
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<Menu> menuList = baseMapper.selectMenuAll();
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleMenuList = baseMapper.selectMenuTree(roleId);
            trees = getTrees(menuList, true, roleMenuList, true);
        }
        else
        {
            trees = getTrees(menuList, false, null, true);
        }
        return trees;
    }
    /**
     * 查询所有菜单
     *
     * @return 菜单列表
     */
    @Override
    public List<Map<String, Object>> menuTreeData() {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<Menu> menuList = baseMapper.selectMenuAll();
        trees = getTrees(menuList, false, null, false);
        return trees;
    }
    /**
     * 查询系统所有权限
     *
     * @return 权限列表
     */
    @Override
    public Map<String, String> selectPermsAll() {
        LinkedHashMap<String, String> section = new LinkedHashMap<>();
        List<Menu> permissions = baseMapper.selectMenuAll();
        if (StringUtils.isNotEmpty(permissions))
        {
            for (Menu menu : permissions)
            {
                section.put(menu.getUrl(), MessageFormat.format(PREMISSION_STRING, menu.getPerms()));
            }
        }
        return section;
    }

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(String menuId) {
        return baseMapper.deleteMenuById(menuId);
    }

    @Override
    public Menu selectMenuById(String menuId) {
        return baseMapper.selectMenuById(menuId);
    }

    /**
     * 查询子菜单数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int selectCountMenuByParentId(String parentId) {
        return baseMapper.selectCountMenuByParentId(parentId);
    }
    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int selectCountRoleMenuByMenuId(String menuId) {
        return roleMenuDao.selectCountRoleMenuByMenuId(menuId);
    }

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(Menu menu) {
        menu.setCreateBy("登录用户");
        return baseMapper.insertMenu(menu);
    }

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int updateMenu(Menu menu) {
        menu.setUpdateBy("登录用户");
        return baseMapper.updateMenu(menu);
    }

    @Override
    public String checkMenuNameUnique(Menu menu) {
        String menuId = StringUtils.isNull(menu.getMenuId()) ? String.valueOf(-1L) : menu.getMenuId();
        Menu info = baseMapper.checkMenuNameUnique(menu.getMenuName());
        if (StringUtils.isNotNull(info) && info.getMenuId() != menuId)
        {
            return UserConstants.MENU_NAME_NOT_UNIQUE;
        }
        return UserConstants.MENU_NAME_UNIQUE;
    }


    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @param isCheck 是否需要选中
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否需要显示权限标识
     * @return
     */
    public List<Map<String, Object>> getTrees(List<Menu> menuList, boolean isCheck, List<String> roleMenuList,
                                              boolean permsFlag)
    {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (Menu menu : menuList)
        {
            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", menu.getMenuId());
            deptMap.put("pId", menu.getParentId());
            deptMap.put("name", transMenuName(menu, roleMenuList, permsFlag));
            deptMap.put("title", menu.getMenuName());
            if (isCheck)
            {
                deptMap.put("checked", roleMenuList.contains(menu.getMenuId() + menu.getPerms()));
            }
            else
            {
                deptMap.put("checked", false);
            }
            trees.add(deptMap);
        }
        return trees;
    }
    public String transMenuName(Menu menu, List<String> roleMenuList, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }
}
