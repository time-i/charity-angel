package com.whu.charityangel.controller;


import com.whu.charityangel.model.entity.Role;
import com.whu.charityangel.model.relation.RolePermission;
import com.whu.charityangel.model.response.ResponseMsg;
import com.whu.charityangel.service.RoleGrantService;
import com.whu.charityangel.service.RoleInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
输入
{
    “role_id”：“”
    “permission_id”：“”
}
 */
@RestController
@RequestMapping("/api/role")
public class RoleGrantController extends BaseController{
    @Autowired
    RoleGrantService rolegrantservice;
    @RequestMapping(value = "/grant", method = RequestMethod.POST)
    //   @RequiresPermissions(value = { "add" })
    public ResponseMsg save(@RequestBody RolePermission rolepermission){
        rolegrantservice.saveData(rolepermission);
        return ResponseMsg.successResponse("OK");
    }

}


