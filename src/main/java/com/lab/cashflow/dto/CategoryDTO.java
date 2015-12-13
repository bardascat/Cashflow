package com.lab.cashflow.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

public class CategoryDTO {

	private int id_category;

	private String name;

	private String description;

	private int id_parent;

	public int getId_category() {
		return id_category;
	}

	public void setId_category(int id_category) {
		this.id_category = id_category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId_parent() {
		return id_parent;
	}

	public void setId_parent(int id_parent) {
		this.id_parent = id_parent;
	}

	@Override
	public String toString() {
		return "CategoryDTO [id_category=" + id_category + ", name=" + name
				+ ", description=" + description + ", id_parent=" + id_parent
				+ "]";
	}

}
