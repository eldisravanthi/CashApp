package com.core.repo;

import java.util.List;

import com.core.beans.Customer;
import com.core.beans.Transactions;

public interface WalletRepo {

	public boolean save(Customer customer);

	public Customer findOne(String mobileNo);

	public void remove(String mobileNo);
	
	public void addTransaction(String mobileNo, Transactions t);
	
	public List<Transactions> getTransaction(String mobileNo);
}
