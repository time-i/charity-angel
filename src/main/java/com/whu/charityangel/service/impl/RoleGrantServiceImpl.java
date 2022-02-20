package com.whu.charityangel.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whu.charityangel.mapper.RoleGrantMapper;
import com.whu.charityangel.model.relation.RolePermission;
import com.whu.charityangel.service.RoleGrantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleGrantServiceImpl extends ServiceImpl<RoleGrantMapper, RolePermission> implements RoleGrantService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveData(RolePermission rolepermission) {
        this.baseMapper.insertData(rolepermission);
    }
}

