package com.whu.charityangel.model.entity;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BankAccount {
    Long id;
    Integer money;
    Long loginId;
    Integer idKind;//0是普通用户，1是项目
}
