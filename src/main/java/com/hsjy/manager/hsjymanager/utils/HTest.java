package com.hsjy.manager.hsjymanager.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hsjy.manager.hsjymanager.entity.Dept;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;
import java.util.Set;

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

        org.springframework.util.MultiValueMap<String,String> multiValueMap = new LinkedMultiValueMap<>();

        multiValueMap.add("早班 9:00-11:00", "周一");
        multiValueMap.add("早班 9:00-11:00", "周二");
        multiValueMap.add("中班 13:00-16:00", "周三");
        multiValueMap.add("早班 9:00-11:00", "周四");
        multiValueMap.add("测试1天2次 09:00 - 12:00", "周五");
        multiValueMap.add("测试1天2次 09:00 - 12:00", "周六");
        multiValueMap.add("中班 13:00-16:00", "周日");
        //打印所有值
        Set<String> keySet = multiValueMap.keySet();
        for (String key : keySet) {
            List<String> values = multiValueMap.get(key);
            System.out.println(StringUtils.join(values.toArray()," ")+":"+key);

        }

    }
}
