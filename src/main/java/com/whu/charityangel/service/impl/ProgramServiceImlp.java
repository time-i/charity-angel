package com.whu.charityangel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whu.charityangel.mapper.ProgramMapper;
import com.whu.charityangel.model.entity.Program;
import com.whu.charityangel.service.ProgramService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImlp extends ServiceImpl<ProgramMapper, Program> implements ProgramService {
    @Override
    public Program InsertProgram(Program program){
        this.baseMapper.insertProgram(program);
        Program programInfo = this.baseMapper.selectAllInfoByName(program.getName());
        return programInfo;
    }

    @Override
    public List<Program> selectAllInfo() {
        return this.baseMapper.selectAllInfo();
    }

    @Override
    public Program selectAllInfoById(Long id){
        return this.baseMapper.selectAllInfoById(id);
    }

    @Override
    public Program selectAllInfoByName(String name){
        return this.baseMapper.selectAllInfoByName(name);
    }

    @Override
    public Program selectAllInfoByContractName(String contractName){
        return this.baseMapper.selectAllInfoByContractName(contractName);
    }

    @Override
    public  List<Program> selectAllInfoByUserId(Long userId){
        return this.baseMapper.selectAllInfoByUserId(userId);
    }

    @Override
    public Integer selectProgramMoneyById(Long id){
        return this.baseMapper.selectProgramMoneyById(id);
    }


    @Override
    public Program setProgramContractName(Program program){
        this.baseMapper.updateProgramContractName(program);
        return program;
    }

    @Override
    public Program setProgramContractAddr(Program program) {
        this.baseMapper.updateProgramContractAddr(program);
        return program;
    }

}
