package com.lab.cashflow.dto;

/**
 * 
 * @author bardcat
 * DTO used for populating general information from the dashboard page
 */
public class HighlevelStats {

	public double totalIncome;
	public double totalExpense;
	private double saved;
	
	
	
	public double getSaved() {
		return (this.totalIncome-this.totalExpense);
	}
	public void setSaved(double saved) {
		this.saved = saved;
	}
	public double getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}
	public double getTotalExpense() {
		return totalExpense;
	}
	public void setTotalExpense(double totalExpense) {
		this.totalExpense = totalExpense;
	}
	@Override
	public String toString() {
		return "HighlevelStats [totalIncome=" + totalIncome + ", totalExpense="
				+ totalExpense + ", saved=" + saved + "]";
	}
	
	
	
}
