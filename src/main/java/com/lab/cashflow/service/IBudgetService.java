package com.lab.cashflow.service;


import java.util.Date;

import com.lab.cashflow.domain.Budget;
import com.lab.cashflow.dto.BudgetDTO;
import com.lab.cashflow.exception.NoBudgetException;

public interface IBudgetService {

    Budget addBudget(BudgetDTO budgetDTO);

    Budget updateBudget(BudgetDTO budgetDto);

    Budget getBudgetByUserId(int id_user);
    
    Budget getMonthlyBudget(Date month) throws NoBudgetException;
    Double getMonthlyExpense(Budget budget);
}
