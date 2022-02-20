
package com.whu.charityangel.controller;



import com.whu.charityangel.model.entity.BankAccount;
import com.whu.charityangel.model.entity.User;
import com.whu.charityangel.model.pojo.IssueInfo;
import com.whu.charityangel.model.response.ResponseMsg;
import com.whu.charityangel.service.BankAccountService;
import com.whu.charityangel.service.UserService;
import com.whu.charityangel.service.impl.CurrentUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "bank", tags = "银行")
@RestController
@RequestMapping("api/bankaccount")
public class BankAccountController {
    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    UserService userService;

    //添加银行账户
    @RequestMapping(value = "/add",method = RequestMethod.POST)
//    @RequiresPermissions(value = {"add"})
    public ResponseMsg insertBankAccount(@RequestBody BankAccount info) {
        BankAccount state;
        state = bankAccountService.InsertBankAccount(info);
        Map result = new HashMap();
        result.put("state",state);
        return ResponseMsg.successResponse(result);
    }

    //查询余额
    @RequestMapping(value = "/query_balance",method = RequestMethod.GET)
    public ResponseMsg QueryBalance(){
        //需要先获取当前用户的id
        //然后将当前用户的id传入下面的函数，返回当前账户的账户信息
        User user = CurrentUserService.getUser();
        BankAccount myself = bankAccountService.queryBankAccountByLoginId(user.getId(),0);
        Map result = new HashMap();
        result.put("balance",myself.getMoney());
        return ResponseMsg.successResponse(result);
    }

    @RequestMapping(value = "/issue",method = RequestMethod.POST)
    public ResponseMsg IssueMoney(@RequestBody IssueInfo info) {
        //查询当前用户的id
        Integer issue = info.getIssue();//充值金额
        User user = userService.getUserByName(info.getUserName());//通过用户名来查找用户所有信息
        //bankAccountService.queryBankAccountByLoginId(user.getId(),0);
        //找到银行账户信息
        BankAccount account = bankAccountService.queryBankAccountByLoginId(user.getId(), 0);
        int state;//是否充值成功的标志
        state = bankAccountService.IssueMoney(issue, account.getId());
        BankAccount myAccount = bankAccountService.queryBankAccountByLoginId(user.getId(), 0);
        Map result = new HashMap();
        result.put("result",state);
        result.put("balance",myAccount.getMoney());
        return ResponseMsg.successResponse(result);
    }


}
