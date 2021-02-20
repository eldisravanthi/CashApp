package com.core.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.core.beans.Customer;
import com.core.beans.Transactions;

public class WalletRepoImpl implements WalletRepo{

	private Map<String, Customer> data; 
	private Map<String, List<Transactions>> transactions;
	
	public WalletRepoImpl() 
	{
		data = new HashMap<String, Customer>();
		transactions = new HashMap<>();
	}

	public boolean save(Customer customer) 
	{
		if(data.get(customer.getMobileNo()) == null) {
			data.put(customer.getMobileNo(), customer);
			return true;
		}
		return false;
	}
	
	@Override
	public void remove(String mobileNo) {
		data.remove(mobileNo);
	}
	
	public Customer findOne(String mobileNo) {
		if(data.get(mobileNo) != null) {
			return data.get(mobileNo);
		}
		return null;
	}

	public void addTransaction(String mobileNo, Transactions t) {
		List<Transactions> list = new ArrayList<>();
		if(transactions.containsKey(mobileNo)) {
			list = transactions.get(mobileNo);
			list.add(t);
			transactions.put(mobileNo, list);
		}
		else {
			list.add(t);
			transactions.put(mobileNo, list);
		}
	}
	public List<Transactions> getTransaction(String mobileNo) {
		return transactions.get(mobileNo);
	}
	
}
