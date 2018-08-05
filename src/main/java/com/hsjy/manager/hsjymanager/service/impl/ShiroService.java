package com.hsjy.manager.hsjymanager.service.impl;

import org.springframework.stereotype.Service;

/**
 * @author zhaodong
 * @version v1.0
 * @email zhaodongxx@outlook.com
 * @since 2018/3/30 22:59
 */
@Service
public class ShiroService {

    public String getPasswordByUsername(String username){
        switch (username){
            case "liming":
                return "123";
            case "hanli":
                return "456";
            default:
                return null;
        }
    }
}