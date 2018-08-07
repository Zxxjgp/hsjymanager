package com.hsjy.manager.hsjymanager.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager.utils
 * @ClassName: MD5Password
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/7 17:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/7 17:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MD5Password {

    public static String generatePassword(String password, String userName){
        String hashAlgorithmName = "MD5";
        ByteSource credentialsSalt = ByteSource.Util.bytes(userName);
        int hashIterations = 1024;

        Object result = new SimpleHash(hashAlgorithmName, password, credentialsSalt, hashIterations);
        return result.toString();
    }
}
