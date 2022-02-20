package com.whu.charityangel.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.whu.charityangel.model.entity.Role;

import java.util.Map;

public interface RoleInsertService extends IService<Role> {
    Map saveData(Role role);

}
