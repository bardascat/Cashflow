package com.lab.cashflow.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", nullable = false)
    private int id_user;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = true)
    private String address;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAccount> userAccount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Transaction> transaction;


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserAccount> getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(List<UserAccount> userAccount) {
        this.userAccount = userAccount;
    }

    public void addTransaction(Transaction transaction) {
        if (this.transaction == null)
            this.transaction = new ArrayList<Transaction>();
        this.transaction.add(transaction);

    }

    @Override
    public String toString() {
        return "User [id_user=" + id_user + ", firstname=" + firstname
                + ", lastname=" + lastname + ", address=" + address
                + ", email=" + email + ", password=" + password
                + ", userAccount=" + userAccount + ", transaction="
                + transaction + "]";
    }


}
