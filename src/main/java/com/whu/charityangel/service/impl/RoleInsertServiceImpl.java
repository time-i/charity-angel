package com.whu.charityangel.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whu.charityangel.mapper.RoleInsertMapper;
import com.whu.charityangel.model.entity.Role;
import com.whu.charityangel.service.RoleInsertService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class RoleInsertServiceImpl extends ServiceImpl<RoleInsertMapper, Role> implements RoleInsertService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map saveData(Role role) {
        this.baseMapper.insertData(role);
        return null;
    }
}