package com.whu.charityangel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whu.charityangel.common.StatusCode;
import com.whu.charityangel.common.exception.BizException;
import com.whu.charityangel.jwt.JwtToken;
import com.whu.charityangel.mapper.UserMapper;
import com.whu.charityangel.model.entity.Role;
import com.whu.charityangel.model.entity.User;
import com.whu.charityangel.model.pojo.UserLoginParam;
import com.whu.charityangel.model.relation.UserRole;
import com.whu.charityangel.service.UserService;
import com.whu.charityangel.utils.JwtUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;


/**
 * @author Chris
 * @date 2021/7/12 21:47
 * @Email:gem7991@dingtalk.com
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public List<String> queryAllPerms(Long userId) {
        return baseMapper.queryAllPerms(userId);
    }

    @Override
    public void authentic(UserLoginParam userEntity) {
        User user = baseMapper.getUserByName(userEntity.getAccount()) ;

        if(null == user){
            throw new BizException(StatusCode.AUTH_INVALID_CLIENT.getRtCode(), "用户名不存在或联盟中无该用户");
        }

        String code = userEntity.getAccount().concat(userEntity.getPassword()).concat(user.getSalt()) ;
        String md5Password = DigestUtils.md5DigestAsHex(code.getBytes());
        if(!user.getPassword().equals(md5Password)){
            user.setAuthenticated(false);
            throw new BizException(StatusCode.AUTH_INVALID_CLIENT.getRtCode(), "密码错误");
        } else {
            user.setAuthenticated(true);
        }
        CurrentUserService.setUser(user);
    }

    @Override
    public User getUserByName(String userName) {
        return baseMapper.getUserByName(userName);
    }

    @Override
    public void updatePassword(String userName, String newPassword) {
        String salt = JwtUtils.generateSalt();
        String code = userName.concat(newPassword).concat(salt) ;
        String md5Password = DigestUtils.md5DigestAsHex(code.getBytes());
        baseMapper.updatePassword(userName,md5Password,salt);
    }

    @Override
    public User addUser(String userName, String password) {
        String salt = JwtUtils.generateSalt();
        String code = userName.concat(password).concat(salt) ;
        String md5Password = DigestUtils.md5DigestAsHex(code.getBytes());
        User newUser = new User();
        newUser.setName(userName);
        newUser.setPassword(md5Password);
        newUser.setSalt(salt);
        baseMapper.insertUser(newUser);
        return newUser;
    }

    @Override
    public UserRole assignUserToRole(String userName, String roleName) {
        User user = baseMapper.getUserByName(userName);
        Role role = baseMapper.getRoleByName(roleName);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(role.getId());
        baseMapper.assignUserToRole(userRole);
        return userRole;
    }

    @Override
    public User getUserById(Long id) {
        return baseMapper.getUserById(id);
    }


}
