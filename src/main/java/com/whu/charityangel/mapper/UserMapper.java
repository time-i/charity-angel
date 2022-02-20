package com.whu.charityangel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.charityangel.model.entity.Role;
import com.whu.charityangel.model.entity.User;
import com.whu.charityangel.model.relation.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper extends BaseMapper<User>{
    User getUserByName(String userName);


    Role getRoleByName(String roleName);

    List<String> queryAllPerms(Long userId);

    /**
     * 修改密码
     */
    void updatePassword(@Param("username") String username, @Param("newPassword") String newPassword,@Param("salt") String salt);

    /**
      添加用户
     */
    @Insert("insert into user(`name`,`password`,`salt`) values(#{name},#{password},#{salt}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);



    /**
     * 分配用户权限
     */
    @Insert("insert into user_role(`user_id`, `role_id`) values(#{userId}, #{roleId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void assignUserToRole(UserRole userRole);

    /**
     * 
     */

    /**
     * 通过用户的id查找用户的名字
     */
    @Select(("select * from user where id = #{userId}"))
    User getUserById(Long userId);
}