package com.whu.charityangel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whu.charityangel.model.relation.RolePermission;

public interface RoleGrantService extends IService<RolePermission> {
    void saveData(RolePermission rolepermission);

}

