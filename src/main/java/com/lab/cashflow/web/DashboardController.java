package com.lab.cashflow.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lab.cashflow.dto.BudgetStatsDTO;
import com.lab.cashflow.dto.CategoriesChartDTO;
import com.lab.cashflow.dto.HighlevelStats;
import com.lab.cashflow.service.IReportsService;

@Controller
public class DashboardController {


    @Autowired
    IReportsService reportSevice;

    private static final Logger logger = Logger.getLogger(DashboardController.class);

    @RequestMapping("/dashboard/getHighLevelStats")
    @ResponseBody
    public HighlevelStats getHighLevelStats() {

    	logger.info("getHighLevelStats - Getting highLevelStats...");
    	
    	HighlevelStats stats = this.reportSevice.getHighLevelStats();
    	
    	logger.info("getHighLevelStats -Returnsing dto...");
    	
    	return stats;
    }
    
    @RequestMapping("/dashboard/getBudgetStats")
    @ResponseBody
    public BudgetStatsDTO getBudgetStats() {

    	logger.info("getBudgetStats - Getting getBudgetStats...");
    	
    	BudgetStatsDTO stats = this.reportSevice.getBudgetStats(new Date());
    	
    	logger.info("getBudgetStats - Returning dto...");
    	
    	return stats;
    }
    
    
    @RequestMapping(value="/dashboard/getCategoriesChartData",method=RequestMethod.GET)
    @ResponseBody
    public CategoriesChartDTO getCategoriesChart(@RequestParam("min") String minDate, @RequestParam("max") String maxdate) throws Exception {

    	logger.info("getCategoriesChart - Getting getCategoriesChart... params: "+minDate + " - "+maxdate);
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	
    	CategoriesChartDTO stats = this.reportSevice.getCategoriesChartData(sdf.parse(minDate),sdf.parse(maxdate));
    	
    	
    	logger.info("getCategoriesChart - Returning dto...");
    	
    	return stats;
    }
    



}
