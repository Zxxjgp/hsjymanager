package com.hsjy.manager.hsjymanager.utils.constant.user;


import com.hsjy.manager.hsjymanager.utils.constant.CodeConstants;
import com.hsjy.manager.hsjymanager.utils.exception.BaseException;

/**
 * 用户信息异常类
 * 
 * @author ruoyi
 */
public class UserException extends BaseException
{

    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super(code, CodeConstants.EX_TOKEN_ERROR_CODE);
    }

}
