package com.lab.cashflow.service;

import com.lab.cashflow.config.security.SecurityUser;
import com.lab.cashflow.domain.Transaction;
import com.lab.cashflow.dto.TransactionDTO;

import java.util.List;

public interface ITransactionService {

	Transaction addTransaction(TransactionDTO transaction);

    List<Transaction> getTransactions(SecurityUser user);

    void deleteTransaction(int id_transaction);

    Transaction getTransaction(int id_transaction);

    Double getTotalIncome();
    
    Double getTotalExpense();

}
