package com.whu.charityangel.model.relation;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRole {
    private long id;
    private long userId;
    private long roleId;
}
