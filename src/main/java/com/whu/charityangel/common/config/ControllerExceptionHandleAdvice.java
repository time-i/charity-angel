package com.whu.charityangel.common.config;


import com.whu.charityangel.common.StatusCode;
import com.whu.charityangel.common.exception.BizException;
import com.whu.charityangel.common.log.SalixLog;
import com.whu.charityangel.model.response.ResponseMsg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@RestControllerAdvice
public class ControllerExceptionHandleAdvice {
    private Logger logger = LogManager.getLogger(ControllerExceptionHandleAdvice.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseMsg handler(HttpServletRequest req, HttpServletResponse res, Exception e) {
        SalixLog salixLog = new SalixLog();
        ResponseMsg responseMsg = new ResponseMsg();
        if (res.getStatus() == HttpStatus.BAD_REQUEST.value()) {
            logger.info("ret_code = " + res.getStatus());
            res.setStatus(HttpStatus.OK.value());
        }

        if (e instanceof NullPointerException) {
            responseMsg = new ResponseMsg(StatusCode.MSG_ERROR.getRtCode(), StatusCode.MSG_ERROR.getMsgCn());
        } else if (e instanceof IllegalArgumentException) {
            responseMsg = new ResponseMsg(StatusCode.MSG_ERROR.getRtCode(), StatusCode.MSG_ERROR.getMsgCn());
        } else if (e instanceof SQLException) {
            responseMsg = new ResponseMsg(StatusCode.MSG_ERROR.getRtCode(), StatusCode.MSG_ERROR.getMsgCn());
        } else if (e instanceof DuplicateKeyException) {
            responseMsg = new ResponseMsg(StatusCode.DATABASE_DUPLICATE_KEY.getRtCode(), StatusCode.DATABASE_DUPLICATE_KEY.getMsgCn());
        } else if (e instanceof BizException) {
            responseMsg = new ResponseMsg(((BizException) e).getErrorCode(), ((BizException) e).getMessage());
        } else if (e instanceof DataIntegrityViolationException) {
            responseMsg = new ResponseMsg(StatusCode.MSG_DATABASE_ERROR.getRtCode(), StatusCode.MSG_DATABASE_ERROR.getMsgCn());
        }else if (e instanceof MethodArgumentNotValidException){
            responseMsg = new ResponseMsg(StatusCode.MSG_DATABASE_ERROR.getRtCode(), StatusCode.MSG_DATABASE_ERROR.getMsgCn());
        } else if (e instanceof UnauthorizedException){
            responseMsg = new ResponseMsg(StatusCode.AUTH_ACCESS_DENIED.getRtCode(), StatusCode.AUTH_ACCESS_DENIED.getMsgCn());
        }
        else {
            logger.warn("catch exception:", e);
            responseMsg = new ResponseMsg(StatusCode.MSG_SERVER_EXCEPTION.getRtCode(), e.getLocalizedMessage());
        }

        return responseMsg;
    }


}

