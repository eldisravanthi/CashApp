package com.core.service;

import java.math.BigDecimal;
import java.util.List;

import com.core.beans.Customer;
import com.core.beans.Transactions;
import com.core.exception.InsufficientBalanceException;
import com.core.exception.InvalidInputException;

public interface WalletService {
	
	public Customer createAccount(String name ,String mobileno, BigDecimal amount) throws InvalidInputException;
	
	public Customer showBalance (String mobileno) throws InvalidInputException;
	
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException;
	
	public Customer depositAmount (String mobileNo,BigDecimal amount ) throws InvalidInputException;
	
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException;
	
	public List<Transactions> getTransactions(String mobileNo) throws InvalidInputException;

}
