package com.pine.moudles.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenbupang on 2018-2-2 21:38
 */
@Controller
@RequestMapping("")
public class TestController {

    @RequestMapping("jsp")
    public String index(HttpServletRequest request){
        request.setAttribute("msg","jsp");
        return "index";
    }
    @RequestMapping("beetl")
    public String beetl(HttpServletRequest request){
        request.setAttribute("msg","beetl");
        return "beetl";
    }
}
