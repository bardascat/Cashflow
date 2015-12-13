package com.lab.cashflow.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BudgetDTO {

    private int id_budget;

    private float amount;

    private int id_user;

    public BudgetDTO() {
    }

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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "BudgetDTO{" +
                "id_budget=" + id_budget +
                ", amount=" + amount +
                ", id_user=" + id_user +
                '}';
    }
}
