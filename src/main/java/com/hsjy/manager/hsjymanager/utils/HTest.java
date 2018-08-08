package com.hsjy.manager.hsjymanager.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hsjy.manager.hsjymanager.entity.Dept;

/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager.utils
 * @ClassName: HTest
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/8 16:41
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/8 16:41
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HTest {
    public static void main(String[] args) {
        Dept dept = new Dept();
        dept.setDeptId("wew");
        System.out.println(JSON.toJSONString(dept));
    }
}
