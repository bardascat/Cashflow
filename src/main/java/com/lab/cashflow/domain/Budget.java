package com.lab.cashflow.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lab.cashflow.dto.BudgetDTO;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "budget")
public class Budget {

    public Budget() {

    }

    public Budget(BudgetDTO dto) {
        this.amount = dto.getAmount();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_budget", nullable = false)
    private int id_budget;

    @Column(nullable = false)
    private float amount;

    @OneToMany(mappedBy="budget")
    private List<Transaction> transactions;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    
    @Column(nullable=false)
    private Date month;
    
  

	public int getId_budget() {
		return id_budget;
	}

	public void setId_budget(int id_budget) {
		this.id_budget = id_budget;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}

	@Override
	public String toString() {
		return "Budget [id_budget=" + id_budget + ", amount=" + amount
				+ ", user=" + user.getEmail()
				+ ", month=" + month + "]";
	}
    
	
    

}
