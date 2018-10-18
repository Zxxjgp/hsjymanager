package com.hsjy.manager.hsjymanager;

import com.hsjy.manager.hsjymanager.entity.User;

/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager
 * @ClassName: Htg
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/9 21:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/9 21:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Htg {
    public static void main(String[] args) {
        User user = new User();
        user.setUserId("12");
        String str = "12";
        System.out.println(user.getUserId().equals(str));
        System.out.println(user.getUserId() == str);
    }
}
