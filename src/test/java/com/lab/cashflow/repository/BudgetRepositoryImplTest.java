package com.lab.cashflow.repository;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lab.cashflow.config.Config;
import com.lab.cashflow.domain.Budget;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={Config.class})
public class BudgetRepositoryImplTest {

	@Autowired
	IBudgetRepository budgetRepository;
	
	private static final Logger logger = Logger.getLogger(BudgetRepositoryImplTest.class);
	
	@Test
	public void getMonthlyBudget(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Budget budget=budgetRepository.getMonthlyBudget(sdf.parse("2015-08-01"));
			logger.info(budget.toString());
			
		}catch(Exception e){
			org.junit.Assert.fail(e.getMessage());
		}
		
	}
	
}
