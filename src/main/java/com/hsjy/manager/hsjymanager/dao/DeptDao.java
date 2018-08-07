package com.hsjy.manager.hsjymanager.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsjy.manager.hsjymanager.entity.Dept;
import com.hsjy.manager.hsjymanager.utils.page.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager.dao
 * @ClassName: DeptDao
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/6 21:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/6 21:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Mapper
public interface DeptDao extends BaseMapper<Dept> {
    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    List<Dept> selectDeptList(Dept dept);
    /**
     * 新增部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
     int insertDept(Dept dept);

    /**
     * 修改部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    int updateDept(Dept dept);
    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    Dept selectDeptById(String deptId);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
    int checkDeptExistUser(String deptId);
}
