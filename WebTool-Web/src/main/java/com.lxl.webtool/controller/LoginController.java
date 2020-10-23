package com.lxl.webtool.controller;

import com.lxl.webtool.commonutils.MyCommonUtils;
import com.lxl.webtool.dao.UsersService;
import com.lxl.webtool.dao.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private UsersService usersService;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping("/name")
    public Map name() {
        String name = MyCommonUtils.getCurUser();
        Users curUser = usersService.findOne(name);
        String aliasName = curUser.getUsernamealias();
        Map map = new HashMap<>();
        map.put("loginName", name);
        map.put("aliasName", aliasName);
        return map;
    }
}
