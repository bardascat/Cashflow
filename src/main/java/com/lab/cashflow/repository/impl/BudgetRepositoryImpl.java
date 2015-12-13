package com.lab.cashflow.repository.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.lab.cashflow.constants.Constants;
import com.lab.cashflow.domain.Budget;
import com.lab.cashflow.exception.NoBudgetException;
import com.lab.cashflow.repository.IBudgetRepository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Transactional
@Repository("BudgetRepository")
public class BudgetRepositoryImpl implements IBudgetRepository {

	private static Logger logger = Logger
			.getLogger(TransactionRepositoryImpl.class);

	@PersistenceContext
	EntityManager em;

	@Override
	public Budget addBudget(Budget budget) {
		logger.info("addBudget - inserting budget...");
		em.persist(budget);
		return budget;
	}

	@Override
	public Budget getBudget(int id_user) {
		try {
			Budget budget = em
					.createQuery(
							"select b from budget b where b.user.id_user=:id_user",
							Budget.class).setParameter("id_user", id_user)
					.getSingleResult();
			return budget;

		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public Budget getMonthlyBudget(Date month) throws NoBudgetException {

		try {

			Calendar c = Calendar.getInstance();

			c.setTime(month);
			c.set(Calendar.DAY_OF_MONTH, 1);

			Date minDate = c.getTime();

			// calculate the max limit
			c.setTime(minDate);
			c.add(Calendar.MONTH, 1);
			c.add(Calendar.DAY_OF_MONTH, -1);

			Date maxDate = c.getTime();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			logger.info("Searching for budget for months:"
					+ sdf.format(minDate) + " - " + sdf.format(maxDate));

			Budget budget = this.em
					.createQuery(
							"select b from budget b where b.month>=:minDate and b.month<=:maxDate",
							Budget.class).setParameter("minDate", minDate)
					.setParameter("maxDate", maxDate).getSingleResult();

			return budget;

		} catch (NoResultException e) {

			throw new NoBudgetException(
					"It seems that you did not define a budget for the current month.");
		}

	}

	@Override
	public Double getMonthlyExpense(Budget budget) {
		Double expense =  this.em.createQuery("select sum(t.amount) from transaction t join t.budget b where b.id_budget=:id_budget and t.type=:expense",Double.class)
		.setParameter("id_budget", budget.getId_budget())
		.setParameter("expense",Constants.EXPENSE_TRANSACTION.toString())
		.getSingleResult();
		
		return expense;
		
		
	}
}
