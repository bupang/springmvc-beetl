package com.pine.moudles.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.pine.moudles.sys.model.entity.auth.User;
import com.pine.moudles.sys.service.auth.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenbupang on 2018-2-2 21:38
 */
@Controller
@RequestMapping("index")
public class TestController {
    @Autowired
    private IUserService userService;

    @RequestMapping("")
    @ResponseBody
    public User index(){
        return userService.selectOne(new EntityWrapper<User>().where("username={0}","admin"));
    }
}
