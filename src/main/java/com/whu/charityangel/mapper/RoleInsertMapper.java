package com.whu.charityangel.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.charityangel.model.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface RoleInsertMapper extends BaseMapper<Role> {

    @Insert("insert into role(`role_name`) values(#{roleName})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertData(Role role);
}
