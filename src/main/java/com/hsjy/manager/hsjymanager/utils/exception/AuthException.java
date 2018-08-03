package com.hsjy.manager.hsjymanager.utils.exception;


import com.hsjy.manager.hsjymanager.utils.constant.CodeConstants;

public class AuthException extends BaseException {
    public AuthException(String message, int states) {
        super(message, CodeConstants.EX_TOKEN_ERROR_CODE);
    }
}
