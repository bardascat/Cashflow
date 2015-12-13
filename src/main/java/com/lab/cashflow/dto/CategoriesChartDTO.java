package com.lab.cashflow.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab.cashflow.domain.Category;

public class CategoriesChartDTO {

	
	private String category_name;
	private double value;
	
	@JsonIgnore
	private Category category;
	
	private Date minDate;
	private Date maxdate;
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Date getMinDate() {
		return minDate;
	}
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	public Date getMaxdate() {
		return maxdate;
	}
	public void setMaxdate(Date maxdate) {
		this.maxdate = maxdate;
	}
	
	
	
	
}
