package com.lab.cashflow.domain;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_account", nullable = false)
    private int id_account;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date cDate;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
