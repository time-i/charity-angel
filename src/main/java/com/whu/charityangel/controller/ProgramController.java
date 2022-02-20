package com.whu.charityangel.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whu.charityangel.chain.ChainFunction;
import com.whu.charityangel.common.ResponseMsg;
import com.whu.charityangel.model.entity.BankAccount;
import com.whu.charityangel.model.entity.Program;
import com.whu.charityangel.model.entity.Transfer;
import com.whu.charityangel.model.entity.User;
import com.whu.charityangel.model.pojo.ProgramParam;
import com.whu.charityangel.model.pojo.TransferInChain;
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

@Api(value = "Program", tags = "项目操作")
@RestController
@RequestMapping("/api/program")
public class ProgramController extends BaseController {
    @Autowired
    ProgramService programService;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    UserService userService;

    @Autowired
    TransferService transferService;

    ChainFunction chainFunction = new ChainFunction();

    /**
     * 创建项目
     * @param
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    //@RequiresPermissions(value = {"Program:add"})
    public ResponseMsg createProgram(@RequestBody ProgramParam programParam) throws IOException {
        /**
         * 插入项目
         */
        Program program = new Program();
        program.setName(programParam.getName());
        program.setDesc(programParam.getDesc());
        program.setRequiredMoney(Integer.parseInt(programParam.getRequiredMoney()));
        program.setDdl(programParam.getDdl());
        program.setStart(new Date());
        User user = CurrentUserService.getUser();
        program.setUserId(user.getId());
        program = programService.InsertProgram(program);
        Map<String, Object> result = new HashMap<>();

        /**
         * 为项目创建银行账户
         */
        BankAccount bankAccount = new BankAccount();
        bankAccount.setIdKind(1); // set for user kind
        bankAccount.setLoginId(program.getId());
        bankAccount.setMoney(0);


        /**
         * 为项目创建合约
         */
        String contractName = chainFunction.deployInit(program.getId(), program.getName(), program.getDdl().toString(), program.getStart().toString(), program.getRequiredMoney());
        String contractHash = chainFunction.getContractHash(contractName);
        /**
         * 插入项目合约名称
         * 插入项目银行账户
         */
        program.setContractName(contractName);
        program.setContractAddr(contractHash);
        BankAccount info = bankAccountService.InsertBankAccount(bankAccount);
        Program programInfo = programService.setProgramContractName(program);
        programInfo = programService.setProgramContractAddr(program);
        result.put("program", programInfo);
        result.put("bank account", "创建成功");
        result.put("account info", info);
        return ResponseMsg.successResponse(result);
    }

    @RequestMapping(value = "/get_all", method = RequestMethod.GET)
    public ResponseMsg getAllInfo() {
        Map<String, Object> result = new HashMap<>();
        List<Program> info = programService.selectAllInfo();
        result.put("program_list", info);
        return ResponseMsg.successResponse(result);
    }

    /**
     * 通过项目id查看项目信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/get_info_by_id", method = RequestMethod.GET)
    public ResponseMsg getInfoById(@RequestParam(value = "id") Long id) {
        Map<String, Object> result = new HashMap<>();
        Program program = programService.selectAllInfoById(id);
        result.put("program", program);
        return ResponseMsg.successResponse(result);
    }

    /**
     * 通过项目名称查看项目信息
     * @param name
     * @return
     */
    @RequestMapping(value = "/get_info_by_name", method = RequestMethod.GET)
    public ResponseMsg getInfoByName(@RequestParam(value = "name") String name) {
        Map<String, Object> result = new HashMap<>();
        Program program = programService.selectAllInfoByName(name);
        result.put("program", program);
        return ResponseMsg.successResponse(result);
    }

    /**
     * 通过user_id查看项目信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/get_info_by_userid", method = RequestMethod.GET)
    public ResponseMsg getInfoByName(@RequestParam(value = "user_id") Long userId) {
        Map<String, Object> result = new HashMap<>();
        List<Program> program = programService.selectAllInfoByUserId(userId);
        result.put("program", program);
        return ResponseMsg.successResponse(result);
    }

    /**
     * 通过项目id查看项目银行账户的金额
     * @param id
     * @return
     */
    @RequestMapping(value = "/get_program_money_by_id", method = RequestMethod.GET)
    public ResponseMsg getAccountMoneyById(@RequestParam(value = "id") Long id) {
        Map<String, Object> result = new HashMap<>();
        //Integer money = programService.selectProgramMoneyById(id);
        // 查询项目的银行账户
        BankAccount account = bankAccountService.queryBankAccountByLoginId(id, 1);
        result.put("account", account);
        return ResponseMsg.successResponse(result);
    }

    /**
     * 通过项目id查看项目的转入记录
     * @return
     */
    @RequestMapping(value = "get_transfer_to_program", method = RequestMethod.GET)
    public ResponseMsg getTransferToProgram(@RequestParam(value = "program_id") Long id) {
        Map<String, Object> result = new HashMap<>();
        // 查询项目的银行账户
        BankAccount account = bankAccountService.queryBankAccountByLoginId(id, 1);
        List<Transfer> transfer = transferService.selectTransferByTo(account.getId());
        result.put("transfer", transfer);
        return ResponseMsg.successResponse(result);
    }

    /**
     * 通过项目id查看项目花销
     * @param id
     * @return
     */
    @RequestMapping(value = "select_program_expenses", method = RequestMethod.GET)
    public ResponseMsg selectProgramExpenses(@RequestParam(value = "program_id") Long id) {
        Map<String, Object> result = new HashMap<>();
        // 查询项目的银行账户
        BankAccount account = bankAccountService.queryBankAccountByLoginId(id, 1);
        List<Transfer> transfer = transferService.selectTransferByFrom(account.getId());
        result.put("transfer", transfer);
        return ResponseMsg.successResponse(result);
    }

    /**
     * 查看项目在区块链上的转入记录
     */
    @RequestMapping(value = "/chain/query_in",method = RequestMethod.GET)
    public ResponseMsg queryTransferInChain(@RequestParam(value = "program_id") Long id) throws IOException {
        Map<String, Object> result = new HashMap<>();
        Program program = programService.selectAllInfoById(id);
        String transfer =  chainFunction.getInfoByTo(program.getContractName(), program.getName());

        JSONArray arr = JSON.parseArray(transfer);
        List<TransferInChain> info = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++) {
            JSONObject json = arr.getJSONObject(i);
            String timeStr = json.getString("time");
            Long timeUnixCode = Long.parseLong(timeStr);
            Date time = new Date(timeUnixCode);
            TransferInChain tmp = new TransferInChain();
            tmp.setId(Long.parseLong(json.getString("Transanction id")));
            tmp.setFrom(json.getString("from"));
            tmp.setTo(program.getName());
            tmp.setAmount(Long.parseLong(json.getString("amount")));
            tmp.setTime(time);
            info.add(tmp);
        }
        result.put("transfer_in", info);
        return ResponseMsg.successResponse(result);
    }

    /**
     * 查看项目在区块链上的转出记录
     */
    @RequestMapping(value = "/chain/query_out",method = RequestMethod.GET)
    public ResponseMsg queryTransferOutChain(@RequestParam(value = "program_id") Long id) throws IOException {
        Map<String, Object> result = new HashMap<>();
        Program program = programService.selectAllInfoById(id);
        String transfer =  chainFunction.getInfoByFrom(program.getContractName(), program.getName());

        JSONArray arr = JSON.parseArray(transfer);
        List<TransferInChain> info = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++) {
            JSONObject json = arr.getJSONObject(i);
            String timeStr = json.getString("time");
            Long timeUnixCode = Long.parseLong(timeStr);
            Date time = new Date(timeUnixCode);
            TransferInChain tmp = new TransferInChain();
            tmp.setId(Long.parseLong(json.getString("Transanction id")));
            tmp.setTo(json.getString("to"));
            tmp.setFrom(program.getName());
            tmp.setAmount(Long.parseLong(json.getString("amount")));
            tmp.setTime(time);
            info.add(tmp);
        }
        result.put("transfer_out", info);
        return ResponseMsg.successResponse(result);
    }
}

