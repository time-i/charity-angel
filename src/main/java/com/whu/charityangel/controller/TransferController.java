
package com.whu.charityangel.controller;


import com.whu.charityangel.chain.ChainFunction;
import com.whu.charityangel.model.entity.BankAccount;
import com.whu.charityangel.model.entity.Program;
import com.whu.charityangel.model.entity.Transfer;
import com.whu.charityangel.model.entity.User;
import com.whu.charityangel.model.pojo.TransferParam;
import com.whu.charityangel.model.pojo.DonationInfo;
import com.whu.charityangel.model.response.ResponseMsg;
import com.whu.charityangel.service.BankAccountService;
import com.whu.charityangel.service.ProgramService;
import com.whu.charityangel.service.TransferService;
import com.whu.charityangel.service.UserService;
import com.whu.charityangel.service.impl.CurrentUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Api(value = "Transfer", tags = "转账操作")
@RestController
@RequestMapping("api/bank")
public class TransferController {
    @Autowired
    TransferService transferService;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    UserService userService;

    @Autowired
    ProgramService programService;

    ChainFunction chainFunction = new ChainFunction();

    /**
     * 用户给项目转账
     * @param _info
     * @return
     */
    @RequestMapping(value = "/user/transfer",method = RequestMethod.POST)
    public ResponseMsg userTransferToProgram(@RequestBody TransferParam _info) throws IOException {
        /**
         * 查询转账双方的用户信息和账户信息
         */
        BankAccount fromAccount;
        BankAccount toAccount;
        _info.setFromKind(0);
        _info.setToKind(1);
        Program fromProgram = null;
        Program toProgram = null;
        Map<String, Object> result = new HashMap<>();

        if (_info.getFromKind().equals(_info.getToKind())) {
            return ResponseMsg.errorResponse("Transfer to same kind is not allowed");
        }
        if (_info.getFromKind() == 0) {
            User fromUser = userService.getUserByName(_info.getFrom());
            fromAccount = bankAccountService.queryBankAccountByLoginId(fromUser.getId(), 0);
        } else {
            fromProgram = programService.selectAllInfoByName(_info.getFrom());
            fromAccount = bankAccountService.queryBankAccountByLoginId(fromProgram.getId(), 1);
        }
        if (_info.getToKind() == 0) {
            User toUser = userService.getUserByName(_info.getTo());
            toAccount = bankAccountService.queryBankAccountByLoginId(toUser.getId(), 0);
        } else {
            toProgram = programService.selectAllInfoByName(_info.getTo());
            toAccount = bankAccountService.queryBankAccountByLoginId(toProgram.getId(), 1);
        }

        if(fromAccount.getMoney() < _info.getMoney()) {
            return ResponseMsg.errorResponse("Insufficient account balance");
        }

        /**
         * 生成转账信息
         */
        Transfer info = new Transfer();
        info.setFrom(fromAccount.getId());
        info.setTo(toAccount.getId());
        info.setMoney(_info.getMoney());

        /**
         * 插入转账信息
         */
        Transfer insertState = transferService.insertTransfer(info);
        /**
         * 执行转账
         */
        int deduceState = transferService.updateSource(info);
        int addState = transferService.updateDestination(info);

        result.put("insert state",insertState);
        result.put("from name", _info.getFrom());
        result.put("to name", _info.getTo());
        result.put("deduce state",deduceState);
        result.put("add state",addState);

        /**
         * 将转账信息上链
         */
        if (_info.getFromKind() == 1 && fromProgram != null) {
            Boolean addFrom  = chainFunction.addTrans(fromProgram.getContractName(), insertState.getId(), _info.getFrom(), _info.getTo(), _info.getMoney());
            result.put("in chain", addFrom);
        }
        if (_info.getToKind() == 1 && toProgram != null) {
            Boolean addTo  = chainFunction.addTrans(toProgram.getContractName(), insertState.getId(), _info.getFrom(), _info.getTo(), _info.getMoney());
            result.put("in chain", addTo);
        }

        return ResponseMsg.successResponse(result);
    }

    /**
     * 项目给用户转账
     * @param _info
     * @return
     */
    @RequestMapping(value = "/program/transfer",method = RequestMethod.POST)
    public ResponseMsg programTransferToUser(@RequestBody TransferParam _info) throws IOException {
        /**
         * 查询转账双方的用户信息和账户信息
         */
        BankAccount fromAccount;
        BankAccount toAccount;
        _info.setFromKind(1);
        _info.setToKind(0);
        Program fromProgram = null;
        Program toProgram = null;
        Map<String, Object> result = new HashMap<>();

        if (_info.getFromKind().equals(_info.getToKind())) {
            return ResponseMsg.errorResponse("Transfer to same kind is not allowed");
        }
        if (_info.getFromKind() == 0) {
            User fromUser = userService.getUserByName(_info.getFrom());
            fromAccount = bankAccountService.queryBankAccountByLoginId(fromUser.getId(), 0);
        } else {
            fromProgram = programService.selectAllInfoByName(_info.getFrom());
            fromAccount = bankAccountService.queryBankAccountByLoginId(fromProgram.getId(), 1);
        }
        if (_info.getToKind() == 0) {
            User toUser = userService.getUserByName(_info.getTo());
            toAccount = bankAccountService.queryBankAccountByLoginId(toUser.getId(), 0);
        } else {
            toProgram = programService.selectAllInfoByName(_info.getTo());
            toAccount = bankAccountService.queryBankAccountByLoginId(toProgram.getId(), 1);
        }

        if(fromAccount.getMoney() < _info.getMoney()) {
            return ResponseMsg.errorResponse("Insufficient account balance");
        }

        /**
         * 生成转账信息
         */
        Transfer info = new Transfer();
        info.setFrom(fromAccount.getId());
        info.setTo(toAccount.getId());
        info.setMoney(_info.getMoney());

        /**
         * 插入转账信息
         */
        Transfer insertState = transferService.insertTransfer(info);
        /**
         * 执行转账
         */
        int deduceState = transferService.updateSource(info);
        int addState = transferService.updateDestination(info);

        result.put("insert state",insertState);
        result.put("from name", _info.getFrom());
        result.put("to name", _info.getTo());
        result.put("deduce state",deduceState);
        result.put("add state",addState);

        /**
         * 将转账信息上链
         */
        if (_info.getFromKind() == 1 && fromProgram != null) {
            Boolean addFrom  = chainFunction.addTrans(fromProgram.getContractName(), insertState.getId(), _info.getFrom(), _info.getTo(), _info.getMoney());
            result.put("in chain", addFrom);
        }
        if (_info.getToKind() == 1 && toProgram != null) {
            Boolean addTo  = chainFunction.addTrans(toProgram.getContractName(), insertState.getId(), _info.getFrom(), _info.getTo(), _info.getMoney());
            result.put("in chain", addTo);
        }

        return ResponseMsg.successResponse(result);
    }
    /**
     * 实现查看自己的捐款记录
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public ResponseMsg queryMyRecord(){
        User user = CurrentUserService.getUser();
        BankAccount account = bankAccountService.queryBankAccountByLoginId(user.getId(), 0);
        List<Transfer> transferInfo = transferService.queryMyRecord(account.getId());
        List<DonationInfo> DonationInfos = new ArrayList<>();
        Map result = new HashMap();
        //捐款记录需要有捐款项目名和金额，因此在pojo中定义一个类donationInfo
        for(int i=0;i<transferInfo.size();i++) {
            //对所有转账信息进行遍历，找到它的目的项目id和数额
            Long toAccountId = transferInfo.get(i).getTo();
            Integer amount = transferInfo.get(i).getMoney();
            Date time = transferInfo.get(i).getDateTime();
            //找到项目名
            BankAccount programAccount = bankAccountService.queryLoginIdByBankAccount(toAccountId);
            Program currentProgram = programService.selectAllInfoById(programAccount.getLoginId());
            //设置当前项目名和项目金额的结构体，加入到结果的数据中
            DonationInfo temp = new DonationInfo();
            temp.setName(currentProgram.getName());
            temp.setMoney(amount);
            temp.setTime(time);
            DonationInfos.add(temp);
        }
        result.put("info", DonationInfos);
        return ResponseMsg.successResponse(result);
    }

    /**
     * 实现查看某个项目的全部捐款记录
     */
    @RequestMapping(value = "/project_fund",method = RequestMethod.GET)
    public ResponseMsg queryProjectFund(@RequestParam("name") String name ) {
        //根据项目名得到项目的详细信息
        Program program = programService.selectAllInfoByName(name);
        //查询项目账户
        BankAccount programAccount = bankAccountService.queryBankAccountByLoginId(program.getId(), 1);
        //根据项目账户id得到项目所有得到的捐款信息
        List<Transfer> transferInfo = transferService.selectTransferByTo(programAccount.getId());
        //生成donationInfo来储存数据
        List<DonationInfo> donationInfos = new ArrayList<>();
        //遍历每一条数据库里的转账信息，筛选出数据库里的转账人名字、金额和转账日期，定义一个getDonationInfo的类
        for(int i=0;i<transferInfo.size();i++) {
            //查询给项目捐助的用户账户
            Long userAccountId = transferInfo.get(i).getFrom();
            BankAccount userAccount = bankAccountService.queryLoginIdByBankAccount(userAccountId);
            //根据用户账户查询用户信息
            User user = userService.getUserById(userAccount.getLoginId());
            Integer amount = transferInfo.get(i).getMoney();
            Date time = transferInfo.get(i).getDateTime();
            DonationInfo temp = new DonationInfo();
            temp.setName(user.getName());
            temp.setMoney(amount);
            temp.setTime(time);
            System.out.println(temp);
            donationInfos.add(temp);
        }
        Map result = new HashMap();
        result.put("info",donationInfos);
        return ResponseMsg.successResponse(result);
    }
}