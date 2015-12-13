package com.lab.cashflow.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.cashflow.config.security.SecurityUser;
import com.lab.cashflow.domain.Budget;
import com.lab.cashflow.domain.Category;
import com.lab.cashflow.domain.Transaction;
import com.lab.cashflow.domain.User;
import com.lab.cashflow.dto.TransactionDTO;
import com.lab.cashflow.repository.ITransactionRepository;
import com.lab.cashflow.service.IBudgetService;
import com.lab.cashflow.service.ICategoryService;
import com.lab.cashflow.service.ITransactionService;
import com.lab.cashflow.service.IUserService;

@Service("TransactionService")
public class TransactionService implements ITransactionService {

	private static final Logger logger = Logger.getLogger(TransactionService.class);
	
    @Autowired
    IUserService userService;

    @Autowired
    ICategoryService categoryService;
    
    @Autowired
    IBudgetService budgetService;

    @Autowired
    ITransactionRepository transactionRepository;

    @Override
    public Transaction addTransaction(TransactionDTO dto) {

        Transaction transaction = new Transaction(dto);

        User user = userService.getUser(dto.getId_user());
        transaction.setUser(user);

        Category category = categoryService.getCategory(dto.getId_category());

        transaction.setCategory(category);

        /**
         * We need to retrieve the budget for this month and associated the transaction to it
         */
        
        Budget budget = this.budgetService.getMonthlyBudget(new Date());
        
        logger.info("addTransaction - Found budget: "+budget.toString());
        
        transaction.setBudget(budget);
        
        return transactionRepository.addTransaction(transaction);

    }

    @Override
    public List<Transaction> getTransactions(SecurityUser user) {
        return transactionRepository.getTransactions(user.getId_user());
    }

    @Override
    public void deleteTransaction(int id_transaction) {
        transactionRepository.deleteTransaction(id_transaction);
    }

    @Override
    public Transaction getTransaction(int id_transaction) {
        return transactionRepository.getTransaction(id_transaction);
    }

	@Override
	public Double getTotalIncome() {
		return this.transactionRepository.getTotalIncome();
	}

	@Override
	public Double getTotalExpense() {
		return this.transactionRepository.getTotalExpense();
	}

}
