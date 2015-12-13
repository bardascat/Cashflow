package com.lab.cashflow.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.cashflow.domain.Budget;
import com.lab.cashflow.dto.BudgetStatsDTO;
import com.lab.cashflow.dto.CategoriesChartDTO;
import com.lab.cashflow.dto.HighlevelStats;
import com.lab.cashflow.repository.ITransactionRepository;
import com.lab.cashflow.service.IBudgetService;
import com.lab.cashflow.service.IReportsService;
import com.lab.cashflow.service.ITransactionService;
import com.lab.cashflow.service.IUserService;

@Service("reportService")
public class ReportService implements IReportsService {

    @Autowired
    IUserService userService;

    
    @Autowired
    ITransactionService transactionService;

    @Autowired
    IBudgetService budgetService;

	@Override
	public HighlevelStats getHighLevelStats() {
		HighlevelStats highLevelStatus = new HighlevelStats();
		
		highLevelStatus.setTotalExpense(this.transactionService.getTotalExpense());
		highLevelStatus.setTotalIncome(this.transactionService.getTotalIncome());
		
		return highLevelStatus;
		
	}

	@Override
	public BudgetStatsDTO getBudgetStats(Date month) {
		BudgetStatsDTO budgetStats=new BudgetStatsDTO();
		
		Budget budget = this.budgetService.getMonthlyBudget(month);
		budgetStats.setBudget(budget);
		
		//get this month expenses
		budgetStats.setSpent(this.budgetService.getMonthlyExpense(budget));
		
		
		
		return budgetStats;
		
	}

	@Override
	public CategoriesChartDTO getCategoriesChartData(Date minDate, Date maxDate) {
		// TODO Auto-generated method stub
		return null;
	}

  
}
