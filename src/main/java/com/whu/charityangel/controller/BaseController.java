package com.whu.charityangel.controller;

import com.whu.charityangel.common.log.SalixLog;

public class BaseController {
    private static ThreadLocal<SalixLog> logThreadLocal = new ThreadLocal<>();

    public SalixLog getSalixLog() {
        return logThreadLocal.get();
    }

    public SalixLog initSalixLog() {
        logThreadLocal.set(new SalixLog());
        return logThreadLocal.get();
    }
}
