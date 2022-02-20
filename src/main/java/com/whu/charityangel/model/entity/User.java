package com.whu.charityangel.model.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author Chris
 * @date 2021/7/12 21:42
 * @Email:gem7991@dingtalk.com
 */

@Data
@ToString
public class User {
    private Long id;
    private String name;
    private String password;
    private String salt;

    private boolean isAuthenticated;
    private int status;
}
