package com.whu.charityangel.service;


import com.alipay.mychain.sdk.vm.abi.datatype.Int;
import com.whu.charityangel.model.entity.BankAccount;

public interface BankAccountService {
    BankAccount InsertBankAccount(BankAccount info);
    BankAccount queryBankAccountByLoginId(Long loginId, Integer idKind);
    BankAccount queryLoginIdByBankAccount(Long accountId);
    int IssueMoney(Integer amount, Long id);
}
