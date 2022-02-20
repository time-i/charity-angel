package com.whu.charityangel.model.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Permission {
    private Long id;
    private String permission;
    private Long roleId;
}
