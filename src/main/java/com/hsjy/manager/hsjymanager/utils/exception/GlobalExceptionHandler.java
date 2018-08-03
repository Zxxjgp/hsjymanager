package com.hsjy.manager.hsjymanager.utils.exception;

import com.hsjy.manager.hsjymanager.utils.constant.CodeConstants;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice("com.hsjy.manager.hsjymanager")
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public BaseResponse baseExceptionHandler(HttpServletResponse response, BaseException ex){
        return new BaseResponse(ex.getStates(),ex.getMessage());

    }

    @ExceptionHandler(Exception.class)
    public BaseResponse otherExceptionHandler(HttpServletResponse response, Exception ex){
        return new BaseResponse(CodeConstants.EX_OTHER_CODE,ex.getMessage());

    }
}
