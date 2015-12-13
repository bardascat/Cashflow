package com.lab.cashflow.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab.cashflow.domain.Budget;

public class BudgetStatsDTO {

	private double value;
	private double spent;
	private double left;
	private double currentMonthOverflow;
	private double lastMonthOverflow;

	@JsonIgnore
	private Budget budget;

	private Date month;

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
		this.value = budget.getAmount();
		this.month = budget.getMonth();
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getSpent() {
		return spent;
	}

	public void setSpent(double spent) {
		this.spent = spent;

		if (this.spent > this.value) {
			this.currentMonthOverflow = this.spent - this.value;
			this.left=0;
		} else {
			this.currentMonthOverflow = 0;
			this.left=this.value-this.spent;
		}

	}

	public double getLeft() {
		return left;
	}

	public void setLeft(double left) {
		this.left = left;
	}

	public double getCurrentMonthOverflow() {
		return currentMonthOverflow;
	}

	public void setCurrentMonthOverflow(double currentMonthOverflow) {
		this.currentMonthOverflow = currentMonthOverflow;
	}

	public double getLastMonthOverflow() {
		return lastMonthOverflow;
	}

	public void setLastMonthOverflow(double lastMonthOverflow) {
		this.lastMonthOverflow = lastMonthOverflow;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

}
