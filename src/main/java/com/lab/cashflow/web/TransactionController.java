package com.lab.cashflow.web;

import com.lab.cashflow.config.security.SecurityUser;
import com.lab.cashflow.constants.Constants;
import com.lab.cashflow.domain.Transaction;
import com.lab.cashflow.dto.TransactionDTO;
import com.lab.cashflow.exception.NoBudgetException;
import com.lab.cashflow.service.ITransactionService;
import com.lab.cashflow.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Controller
public class TransactionController {


    @Autowired
    IUserService userService;

    @Autowired
    ITransactionService transactionService;

    private static final Logger logger = Logger.getLogger(TransactionController.class);

    @RequestMapping(value = "/transaction/add", method = RequestMethod.POST)
    @ResponseBody
    public Transaction addTransaction(@RequestBody TransactionDTO dto) throws NoBudgetException {

        logger.info(dto.toString());
        SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getDetails();

        logger.info("Principal:" + user);

        Transaction transaction = new Transaction(dto);

        dto.setId_user(user.getId_user());

        Transaction trs = transactionService.addTransaction(dto);

        return transaction;

    }


    @RequestMapping(value = "/transaction/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Transaction> loadTransactions() {

        SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getDetails();

        List<Transaction> transactions = transactionService.getTransactions(user);

        return transactions;

    }

    @RequestMapping(value = "/transaction/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteTransaction(@PathVariable Integer id) {

        transactionService.deleteTransaction(id);

        return ResponseEntity.ok(OK);

    }

    @RequestMapping(value = "/transaction/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Transaction getTransaction(@PathVariable Integer id) {

        return transactionService.getTransaction(id);

    }


}
