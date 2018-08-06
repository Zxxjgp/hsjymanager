package com.hsjy.manager.hsjymanager.utils.constant.user;

/**
 * 用户不存在异常类
 * 
 * @author ruoyi
 */
public class UserNotExistsException extends UserException
{

    private static final long serialVersionUID = 1L;

    public UserNotExistsException()
    {
        super("用户名或密码为空", null);
    }
}
