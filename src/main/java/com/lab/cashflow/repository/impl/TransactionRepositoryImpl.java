package com.lab.cashflow.repository.impl;

import com.lab.cashflow.constants.Constants;
import com.lab.cashflow.domain.Transaction;
import com.lab.cashflow.repository.ITransactionRepository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

@Transactional
@Repository("TransactionRepository")
public class TransactionRepositoryImpl implements ITransactionRepository {

    private static Logger logger = Logger.getLogger(TransactionRepositoryImpl.class);

    @PersistenceContext
    EntityManager em;

    @Override
    public Transaction getTransaction(int id_transaction) {
        return em.find(Transaction.class, id_transaction);
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        logger.info("addTransaction - inserting transaction...");
        if (transaction.getId_transaction() == 0)
            em.persist(transaction);
        else
           return em.merge(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> getTransactions(int id_user) {
        return em.createQuery("select t from transaction t where t.user.id_user=:id_user", Transaction.class)
                .setParameter("id_user", id_user)
                .getResultList();
    }

    @Override
    public void deleteTransaction(int id_transaction) {
        logger.info("deleteTransaction - deleting transaction...");
        em.remove(getTransaction(id_transaction));
    }
    
    @Override
	public Double getTotalIncome() {
		
		TypedQuery<Double> query = this.em.createQuery("select sum(t.amount) from transaction t where t.type=:type",Double.class);
		query.setParameter("type", Constants.INCOME_TRANSACTION.toString());
		Double result = query.getSingleResult();
		
		return result;
		
	}


	@Override
	public Double getTotalExpense() {
		TypedQuery<Double> query = this.em.createQuery("select sum(t.amount) from transaction t where t.type=:type",Double.class);
		query.setParameter("type", Constants.EXPENSE_TRANSACTION.toString());
		Double result = query.getSingleResult();
		
		return result;
	}

}
