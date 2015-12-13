package com.lab.cashflow.web;

import com.lab.cashflow.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {


    @Autowired
    IUserService userService;

    private static final Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping("/")
    @ResponseBody
    public String hello() {

        return "Welcome...";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin() {

        return "hello admin...";
    }


}
