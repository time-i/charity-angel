package com.whu.charityangel.service.impl;


import com.whu.charityangel.model.entity.User;


public class CurrentUserService {
    private static ThreadLocal<User> threadLocal = new ThreadLocal();

    public static void setUser(User user){
        threadLocal.set(user);
    }

    public static User getUser(){
        if(threadLocal.get() == null){
            threadLocal.set(new User());
        }
        return threadLocal.get();
    }
}
