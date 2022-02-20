package com.whu.charityangel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whu.charityangel.model.entity.BankAccount;
import com.whu.charityangel.model.entity.Program;
import com.whu.charityangel.model.entity.Transfer;

import java.util.List;

public interface ProgramService extends IService<Program> {
    Program InsertProgram(Program program);
    List<Program> selectAllInfo();
    Program selectAllInfoById(Long id);
    Program selectAllInfoByName(String name);
    Program selectAllInfoByContractName(String contractName);
    List<Program> selectAllInfoByUserId(Long userId);
    Integer selectProgramMoneyById(Long id);
    Program setProgramContractName(Program program);
    Program setProgramContractAddr(Program program);
}
