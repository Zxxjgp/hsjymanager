package com.hsjy.manager.hsjymanager.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsjy.manager.hsjymanager.dao.DeptDao;
import com.hsjy.manager.hsjymanager.entity.Dept;
import com.hsjy.manager.hsjymanager.service.DeptService;
import com.hsjy.manager.hsjymanager.utils.constant.CodeConstants;
import com.hsjy.manager.hsjymanager.utils.exception.AuthException;
import com.hsjy.manager.hsjymanager.utils.page.Page;
import com.hsjy.manager.hsjymanager.utils.result.ResultGenerator;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager.service.impl
 * @ClassName: DeptServiceImpl
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/6 21:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/6 21:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao,Dept> implements DeptService {

    @Override
    public List<Dept> selectDeptList( Dept dept) {
        List<Dept> list = baseMapper.selectDeptList(dept);
        return list;
    }

    @Override
    public int insertDept(Dept dept) {
        return baseMapper.insertDept(dept);
    }

    @Override
    public int updateDept(Dept dept) {
        int result = 0;
        try {
            result = baseMapper.updateDept(dept);
        } catch ( Exception e ) {
            throw new AuthException("更新出现异常信息",CodeConstants.UPDATE_EXCEPTION);
        }
        return result;
    }

    @Override
    public Dept selectDeptById(String deptId) {
        return baseMapper.selectDeptById(deptId);
    }

    @Override
    public int checkDeptExistUser(String deptId) {
        return baseMapper.checkDeptExistUser(deptId);
    }
}
