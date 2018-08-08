package com.hsjy.manager.hsjymanager.controller;

import com.hsjy.manager.hsjymanager.entity.Menu;
import com.hsjy.manager.hsjymanager.entity.Role;
import com.hsjy.manager.hsjymanager.service.MenuService;
import com.hsjy.manager.hsjymanager.utils.StringUtils;
import com.hsjy.manager.hsjymanager.utils.constant.CodeConstants;
import com.hsjy.manager.hsjymanager.utils.exception.AuthException;
import com.hsjy.manager.hsjymanager.utils.result.Result;
import com.hsjy.manager.hsjymanager.utils.result.ResultGenerator;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 菜单信息
 * 
 * @author jgp
 */
@Controller
@RequestMapping("/menu")
public class MenuController
{

    private String prefix = "system/menu";

    @Autowired
    private MenuService menuService;

    @GetMapping()
    public String menu()
    {
        return prefix + "/menu";
    }

    @GetMapping("list")
    @ResponseBody
    public List<Menu> list(Menu menu)
    {
        List<Menu> menuList = menuService.selectMenuList(menu);
        return menuList;
    }

    /**
     * 删除菜单
     */
    @PostMapping("/remove/{menuId}")
    @ResponseBody
    public Result remove(@PathVariable("menuId") String menuId)
    {
        if (menuService.selectCountMenuByParentId(menuId) > 0)
        {
            throw  new  AuthException("存在子菜单,不允许删除",CodeConstants.DELETE_EXCEPTION);
        }
        if (menuService.selectCountRoleMenuByMenuId(menuId) > 0)
        {
            throw new AuthException("菜单已分配,不允许删除",CodeConstants.DELETE_EXCEPTION);
        }
        return ResultGenerator.genSuccessResult(menuService.deleteMenuById(menuId));
    }

    /**
     * 新增
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") String parentId, ModelMap mmap)
    {
        Menu menu = null;
        if (String.valueOf(0L) != parentId)
        {
            menu = menuService.selectMenuById(parentId);
        }
        else
        {
            menu = new Menu();
            menu.setMenuId(String.valueOf(0L));
            menu.setMenuName("主目录");
        }
        mmap.put("menu", menu);
        return prefix + "/add";
    }

    /**
     * 新增保存菜单
     */

    @PostMapping("/add")
    @ResponseBody
    public Result addSave(Menu menu)
    {
        return ResultGenerator.genSuccessResult(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @GetMapping("/edit/{menuId}")
    public String edit(@PathVariable("menuId") String menuId, ModelMap mmap)
    {
        mmap.put("menu", menuService.selectMenuById(menuId));
        return prefix + "/edit";
    }

    /**
     * 修改保存菜单
     */

    @PostMapping("/edit")
    @ResponseBody
    public Result editSave(Menu menu)
    {
        return ResultGenerator.genSuccessResult(menuService.updateMenu(menu));
    }

    /**
     * 选择菜单图标
     */
    @GetMapping("/icon")
    public String icon()
    {
        return prefix + "/icon";
    }

    /**
     * 校验菜单名称
     */
    @PostMapping("/checkMenuNameUnique")
    @ResponseBody
    public String checkMenuNameUnique(Menu menu)
    {
        String uniqueFlag = "0";
        if (StringUtils.isNotNull(menu))
        {
            uniqueFlag = menuService.checkMenuNameUnique(menu);
        }
        return uniqueFlag;
    }

    /**
     * 加载角色菜单列表树
     */
    @GetMapping("/roleMenuTreeData")
    @ResponseBody
    public List<Map<String, Object>> roleMenuTreeData(Role role)
    {
        List<Map<String, Object>> tree = menuService.roleMenuTreeData(role);
        return tree;
    }

    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/menuTreeData")
    @ResponseBody
    public List<Map<String, Object>> menuTreeData(Role role)
    {
        List<Map<String, Object>> tree = menuService.menuTreeData();
        return tree;
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{menuId}")
    public String selectMenuTree(@PathVariable("menuId") String menuId, ModelMap mmap)
    {
        mmap.put("treeName", menuService.selectMenuById(menuId).getMenuName());
        return prefix + "/tree";
    }
}