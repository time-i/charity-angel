package com.whu.charityangel.jwt;

import javax.servlet.http.HttpServletRequest;

import com.whu.charityangel.common.SalixError;
import com.whu.charityangel.model.response.ResponseMsg;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Chris
 * @date 2021/7/9 13:11
 * @Email:gem7991@dingtalk.com
 */

@RestControllerAdvice
public class ExceptionController {

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public ResponseMsg handleShiroException(ShiroException e) {
        return ResponseMsg.errorResponse(SalixError.MSG_ERROR,e.getMessage());
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public ResponseMsg globalException(HttpServletRequest request, Throwable ex) {
        return ResponseMsg.errorResponse(SalixError.MSG_ERROR,ex.getMessage());
    }
}

