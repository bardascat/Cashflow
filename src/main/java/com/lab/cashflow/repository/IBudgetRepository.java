package com.lab.cashflow.repository;

import java.util.Date;

import com.lab.cashflow.domain.Budget;
import com.lab.cashflow.exception.NoBudgetException;

public interface IBudgetRepository {

    Budget addBudget(Budget budget);

    Budget getBudget(int id_user);
    
    Budget getMonthlyBudget(Date month) throws NoBudgetException;
    
    Double getMonthlyExpense(Budget budget);
    
}
