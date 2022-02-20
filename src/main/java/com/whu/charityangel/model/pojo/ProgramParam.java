package com.whu.charityangel.model.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ProgramParam {
    String name;
    String desc;
    String requiredMoney;
    Date ddl;
}
