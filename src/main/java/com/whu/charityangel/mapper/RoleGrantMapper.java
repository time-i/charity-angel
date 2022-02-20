package com.whu.charityangel.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.charityangel.model.relation.RolePermission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface RoleGrantMapper extends BaseMapper<RolePermission> {

    @Insert("insert into role_permission(`role_id`,`permission_id`) values(#{role_id},#{permission_id})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertData(RolePermission rolepermission);
}