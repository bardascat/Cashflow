package com.lab.cashflow.service;

import java.util.Date;

import com.lab.cashflow.dto.BudgetStatsDTO;
import com.lab.cashflow.dto.CategoriesChartDTO;
import com.lab.cashflow.dto.HighlevelStats;

public interface IReportsService {

   HighlevelStats getHighLevelStats();
   BudgetStatsDTO getBudgetStats(Date month);
   CategoriesChartDTO getCategoriesChartData(Date minDate, Date maxDate);

}
