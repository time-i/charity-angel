package com.whu.charityangel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whu.charityangel.mapper.BankAccountMapper;
import com.whu.charityangel.model.entity.BankAccount;
import com.whu.charityangel.service.BankAccountService;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl extends ServiceImpl<BankAccountMapper, BankAccount> implements BankAccountService {

    @Override
    public BankAccount InsertBankAccount(BankAccount info) {
        baseMapper.insertAccount(info);
        return info;
    }

    @Override
    public BankAccount queryBankAccountByLoginId(Long loginId, Integer idKind) {
        return baseMapper.queryBankAccountByLoginId(loginId, idKind);
    }

    @Override
    public BankAccount queryLoginIdByBankAccount(Long accountId) {
        return baseMapper.queryLoginIdByBankAccount(accountId);
    }

    @Override
    public int IssueMoney(Integer amount,Long id) {
        return baseMapper.IssueMoney(amount,id);
    }
}
