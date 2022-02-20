package com.whu.charityangel.model.relation;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RolePermission {
    private long id;
    private long role_id;
    private long permission_id;
}
