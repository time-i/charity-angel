package com.whu.charityangel.model.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Program {
    Long id;
    String name;
    Long userId;
    String desc;
    Integer requiredMoney;
    Date start;
    Date ddl;
    String contractName;
    String contractAddr;
}
