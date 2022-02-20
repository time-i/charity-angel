package com.whu.charityangel.controller;


import com.whu.charityangel.model.entity.Role;
import com.whu.charityangel.model.response.ResponseMsg;
import com.whu.charityangel.service.RoleInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*
 输入
 {
 "roleName":""
 }
 成功返回“ok”
*/

@RestController
@RequestMapping("/api/role")
public class RoleInsertController extends BaseController{
    @Autowired
    RoleInsertService roleinsertservice;
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    //   @RequiresPermissions(value = { "add" })
    public ResponseMsg save(@RequestBody Role role) {
        roleinsertservice.saveData(role);
        return ResponseMsg.successResponse("ok！");
    }

}
