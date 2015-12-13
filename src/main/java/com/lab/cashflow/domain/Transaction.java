package com.lab.cashflow.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lab.cashflow.dto.TransactionDTO;

import javax.persistence.*;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "transaction")
public class Transaction {


    public Transaction() {

    }

    public Transaction(TransactionDTO dto) {
        if (dto.getId_transaction() != 0)
            this.id_transaction = dto.getId_transaction();
        this.description = dto.getDescription();
        this.amount = dto.getAmount();
        this.payment_method = dto.getPayment_method();
        this.type = dto.getType();
        this.date = dto.getDate();
        this.setType(dto.getType());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_transaction", nullable = false)
    private int id_transaction;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private float amount;

    @Column(nullable = false)
    private String payment_method;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @Column
    private Date date;

    
    @ManyToOne
    @JoinColumn(name="id_budget")
    private Budget budget;
    
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public int getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(int id_transaction) {
        this.id_transaction = id_transaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

    

}
