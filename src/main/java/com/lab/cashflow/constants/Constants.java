package com.lab.cashflow.constants;

public enum Constants {
	
	INCOME_TRANSACTION("INCOME_TRANSACTION"),
	EXPENSE_TRANSACTION("EXPENSE_TRANSACTION");
	
	
	private String value;
	
	Constants(String value){
		this.value=value;
	}
	
}
