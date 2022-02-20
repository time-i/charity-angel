package com.whu.charityangel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whu.charityangel.model.entity.Role;
import com.whu.charityangel.model.entity.User;
import com.whu.charityangel.model.pojo.UserLoginParam;
import com.whu.charityangel.model.relation.UserRole;

import java.util.List;

/**
 * @author Chris
 * @date 2021/7/12 21:45
 * @Email:gem7991@dingtalk.com
 */

public interface UserService extends IService<User>{
    List<String> queryAllPerms(Long userId);
    void authentic(UserLoginParam userEntity);
    User getUserByName(String userName);
    void updatePassword(String userName, String newPassword);
    User addUser(String userName, String password);
    UserRole assignUserToRole(String userName, String roleName);
    User getUserById(Long id);
}
