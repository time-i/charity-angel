package com.whu.charityangel.controller;

import com.whu.charityangel.common.SalixError;
import com.whu.charityangel.common.log.SalixLog;
import com.whu.charityangel.model.entity.User;
import com.whu.charityangel.model.pojo.UserLoginParam;
import com.whu.charityangel.model.response.ResponseMsg;
import com.whu.charityangel.service.BankAccountService;
import com.whu.charityangel.service.UserService;
import com.whu.charityangel.service.impl.CurrentUserService;
import com.whu.charityangel.utils.JwtUtils;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Api(value = "Login", tags = "登录验证")
@CrossOrigin(maxAge = 3600)
@RestController
public class AuthController extends BaseController{

    private final Logger logger = LogManager.getLogger(AuthController.class);

    @Autowired
    UserService userService;

    @Autowired
    BankAccountService bankaccountservice;

    /**
     * 登录
     * @param userLoginParam {"account":, "password": }
     * @return {"msgId", "data", "rtCode", "message"}
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseMsg login(@RequestBody UserLoginParam userLoginParam){
        SalixLog salixLog = new SalixLog();
        salixLog.add("account", userLoginParam.getAccount());
        salixLog.add("password", userLoginParam.getPassword());
        logger.info(salixLog.toString());
        if(userLoginParam.getAccount() == null || userLoginParam.getPassword() == null){
            return ResponseMsg.errorResponse(SalixError.MSG_USER_NAME_PASSWORD_NULL);
        }

        userService.authentic(userLoginParam);
        if(!CurrentUserService.getUser().isAuthenticated()) {
            return ResponseMsg.errorResponse(SalixError.MSG_USER_VERIFY_ERROR);
        }

        User user = CurrentUserService.getUser();
        String jwtToken = JwtUtils.sign(user.getName(), JwtUtils.SECRET);
        Map<String, Object> result = new HashMap<>();
        result.put("token",jwtToken);

        logger.info(user.getName() +"登录");

        return ResponseMsg.successResponse(result);
    }

}
