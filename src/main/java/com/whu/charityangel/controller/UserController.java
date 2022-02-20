package com.whu.charityangel.controller;

import com.whu.charityangel.common.SalixError;
import com.whu.charityangel.common.log.SalixLog;
import com.whu.charityangel.model.entity.BankAccount;
import com.whu.charityangel.model.entity.User;
import com.whu.charityangel.model.pojo.UserLoginParam;
import com.whu.charityangel.model.relation.UserRole;
import com.whu.charityangel.model.response.ResponseMsg;
import com.whu.charityangel.service.BankAccountService;
import com.whu.charityangel.service.UserService;
import com.whu.charityangel.service.impl.CurrentUserService;
import com.whu.charityangel.utils.JwtUtils;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.lang.System.getProperties;


@Api(value = "User", tags = "用户操作")
@CrossOrigin(maxAge = 3600)
@RequestMapping("api/user")
@RestController
public class UserController extends BaseController{

    private final Logger logger = LogManager.getLogger(AuthController.class);

    @Autowired
    UserService userService;

    @Autowired
    BankAccountService bankaccountservice;

    /**
     * 用户注册, 会自动创建一个银行账户
     * @param userLoginParam {"account":, "password": }
     * @return {"msgId", "data", "rtCode", "message"}
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseMsg register(@RequestBody UserLoginParam userLoginParam){
        SalixLog salixLog = new SalixLog();
        salixLog.add("account", userLoginParam.getAccount());
        salixLog.add("password", userLoginParam.getPassword());
        logger.info(salixLog.toString());
        if(userLoginParam.getAccount() == null || userLoginParam.getPassword() == null){
            return ResponseMsg.errorResponse(SalixError.MSG_USER_NAME_PASSWORD_NULL);
        }

        User newUser = userService.addUser(userLoginParam.getAccount(), userLoginParam.getPassword());


        logger.info(userLoginParam.getAccount() +"注册");
        Map<String, Object> result = new HashMap<>();
        result.put("register","注册成功");
        result.put("username", newUser.getName());

        UserRole userRole = userService.assignUserToRole(newUser.getName(), "user");
        result.put("assign", "user授权成功");
        result.put("role", "user");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setIdKind(0); // set for user kind
        bankAccount.setLoginId(newUser.getId());
        bankAccount.setMoney(0);
        BankAccount info = bankaccountservice.InsertBankAccount(bankAccount);
        result.put("bank account", "创建成功");
        result.put("account info", info);

        return ResponseMsg.successResponse(result);
    }

    /**
     * 管理员注册
     * @param userLoginParam {"account":, "password": }
     * @return {"msgId", "data", "rtCode", "message"}
     */
    @RequestMapping(value = "/admin/register", method = RequestMethod.POST)
    @RequiresPermissions(value = { "account:add" })
    public ResponseMsg adminRegister(@RequestBody UserLoginParam userLoginParam){
        SalixLog salixLog = new SalixLog();
        salixLog.add("account", userLoginParam.getAccount());
        salixLog.add("password", userLoginParam.getPassword());
        logger.info(salixLog.toString());
        if(userLoginParam.getAccount() == null || userLoginParam.getPassword() == null){
            return ResponseMsg.errorResponse(SalixError.MSG_USER_NAME_PASSWORD_NULL);
        }

        User newUser = userService.addUser(userLoginParam.getAccount(), userLoginParam.getPassword());


        logger.info(userLoginParam.getAccount() +"注册");
        Map<String, Object> result = new HashMap<>();
        result.put("register","管理员注册成功");
        result.put("user", newUser);

        UserRole userRole = userService.assignUserToRole(newUser.getName(), "admin");
        result.put("assign", "管理员授权成功");
        result.put("role", "admin");
        return ResponseMsg.successResponse(result);
    }

    /**
     * 获取当前用户信息
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseMsg currentUser() {
        Map<String, Object> result = new HashMap<>();
        User user = CurrentUserService.getUser();
        result.put("user", user);
        return ResponseMsg.successResponse(result);
    }

}
