package com.lab.cashflow.web;


import com.lab.cashflow.config.security.SecurityUser;
import com.lab.cashflow.domain.Budget;
import com.lab.cashflow.dto.BudgetDTO;
import com.lab.cashflow.service.IBudgetService;
import com.lab.cashflow.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BudgetController {

    @Autowired
    IUserService userService;

    @Autowired
    IBudgetService budgetService;

    private static final Logger logger = Logger.getLogger(BudgetController.class);

    @RequestMapping(value = "/budget/add", method = RequestMethod.POST)
    @ResponseBody
    public Budget addBudget(@RequestBody BudgetDTO budgetDto) {

        logger.info(budgetDto.toString());

        if (budgetDto.getId_user() != 0) {
            return budgetService.updateBudget(budgetDto);
        }

        SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getDetails();

        logger.info("Principal:" + user);

        budgetDto.setId_user(user.getId_user());

        Budget budget = budgetService.addBudget(budgetDto);

        return budget;

    }

    @RequestMapping(value = "/budget", method = RequestMethod.GET)
    @ResponseBody
    public Budget addBudget() {

        SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getDetails();

        return budgetService.getBudgetByUserId(user.getId_user());

    }
}
