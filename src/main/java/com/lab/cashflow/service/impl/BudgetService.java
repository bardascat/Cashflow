package com.lab.cashflow.service.impl;

import java.util.Date;

import com.lab.cashflow.domain.Budget;
import com.lab.cashflow.domain.User;
import com.lab.cashflow.dto.BudgetDTO;
import com.lab.cashflow.exception.NoBudgetException;
import com.lab.cashflow.repository.IBudgetRepository;
import com.lab.cashflow.service.IBudgetService;
import com.lab.cashflow.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BudgetService")
public class BudgetService implements IBudgetService {

    @Autowired
    IUserService userService;

    @Autowired
    IBudgetRepository budgetRepository;

    @Override
    public Budget addBudget(BudgetDTO budgetDto) {

        Budget budget = new Budget(budgetDto);

        User user = userService.getUser(budgetDto.getId_user());

        Budget b = budgetRepository.getBudget(user.getId_user());

        budget.setUser(user);

        budget.setMonth(new Date());
        
        
        return budgetRepository.addBudget(budget);

    }

    @Override
    public Budget updateBudget(BudgetDTO budgetDto) {
        Budget budget = new Budget(budgetDto);

        return budgetRepository.addBudget(budget);

    }

    @Override
    public Budget getBudgetByUserId(int id_user) {
        return budgetRepository.getBudget(id_user);
    }

	@Override
	public Budget getMonthlyBudget(Date month) throws NoBudgetException{
		return this.budgetRepository.getMonthlyBudget(month);
		
	}

	@Override
	public Double getMonthlyExpense(Budget budget) {
		return this.budgetRepository.getMonthlyExpense(budget);
	}
    
    
    
}
