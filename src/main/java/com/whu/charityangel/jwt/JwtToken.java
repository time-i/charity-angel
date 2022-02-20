package com.whu.charityangel.jwt;

import com.whu.charityangel.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Chris
 * @date 2021/7/9 12:47
 * @Email:gem7991@dingtalk.com
 */

public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    // 加密后的 JWT token串
    private String token;

    private String userName;

    public JwtToken(String token) {
        this.token = token;
        this.userName = JwtUtils.getClaimFiled(token, "username");
    }

    @Override
    public Object getPrincipal() {
        return this.userName;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
