package com.lab.cashflow.repository;

import com.lab.cashflow.domain.Transaction;
import com.lab.cashflow.dto.BudgetStatsDTO;

import java.util.List;

public interface ITransactionRepository {

    Transaction getTransaction(int id_transaction);

    Transaction addTransaction(Transaction transaction);

    List<Transaction> getTransactions(int id_user);

    Double getTotalIncome();
    Double getTotalExpense();
   
    
    void deleteTransaction(int id_transaction);

}
