package com.whu.charityangel.model.pojo;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class UserLoginParam {
    private String account;
    private String password;
}
