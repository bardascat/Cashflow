package com.lab.cashflow.web;

import com.lab.cashflow.dto.CsrfTokenDto;
import com.lab.cashflow.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class SecurityController {


    @Autowired
    IUserService userService;

    private static final Logger logger = Logger.getLogger(SecurityController.class);

    @RequestMapping("/login.do")
    public String testSecurity() {

        return "login-page";
    }

    @RequestMapping("/public/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/public/getCsrfToken.do")
    @ResponseBody
    public CsrfTokenDto getCsrfToken(HttpServletRequest request) {

        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
                .getName());
        CsrfTokenDto tokenDto = new CsrfTokenDto(csrf);

        return tokenDto;
    }


}
