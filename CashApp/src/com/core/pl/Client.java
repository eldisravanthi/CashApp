package com.core.pl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.core.beans.Customer;
import com.core.beans.Transactions;
import com.core.beans.Wallet;
import com.core.exception.InsufficientBalanceException;
import com.core.exception.InvalidInputException;
import com.core.service.WalletService;
import com.core.service.WalletServiceImpl;

public class Client 
{
	WalletService service;
	
	Client() {
		service = new WalletServiceImpl();
	}
	
	
	public void menu() {
		System.out.println("1) Create Account");
		System.out.println("2) Show Balance");
		System.out.println("3) Deposit Amount");
		System.out.println("4) Withdraw Amount");
		System.out.println("5) Fund Transfer");
		System.out.println("6) Print Transactions");
		System.out.println("0) Exit Application");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter your choice");
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1:
				Customer customer = new Customer();// creating cus-->this is a constructor
				Wallet wallet = new Wallet();//creatimg wallet
				
				System.out.print("Enter name: ");
				String name = sc.next();
				
				System.out.print("Enter mobileNumber: ");
				String mobileNumber = sc.next();
				
				System.out.print("Enter Amount: ");
				BigDecimal amount = sc.nextBigDecimal();
				
				try 
				{
					customer = service.createAccount(name, mobileNumber, amount);
					System.out.println("Your account has successfully registered");
				} 
				catch (InvalidInputException e) {
					e.printStackTrace();
				}
				break;
				
			case 2:
				System.out.println("Enter mobile number");
				mobileNumber = sc.next();
				
			try {
				customer = service.showBalance(mobileNumber);
				System.out.print("The balance in account " + customer.getName());
				System.out.println(" is " + customer.getWallet().getBalance());
			} catch (InvalidInputException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
				
				break;
			
			case 3:
				System.out.println("Enter mobile number");
				mobileNumber = sc.next();
				
				System.out.println("Enter amount to be deposited");
				amount = sc.nextBigDecimal();
				
				try 
				{
					customer = service.depositAmount(mobileNumber, amount);
					System.out.println("Successfully deposited");
					System.out.println("Account balance is: " + customer.getWallet().getBalance());
				} 
				catch (InvalidInputException e2) 
				{
					e2.printStackTrace();
				}
				
				
				
				break;
			
			case 4:
				System.out.println("Enter mobile number");
				mobileNumber = sc.next();
				
				System.out.println("Enter amount to be withdrawn");
				amount = sc.nextBigDecimal();
				
				try 
				{
					customer = service.withdrawAmount(mobileNumber, amount);
					System.out.println("Successfully withdrawn");
					System.out.println("Account balance is: " + customer.getWallet().getBalance());
				}
				catch (InvalidInputException e1) 
				{
					e1.printStackTrace();
				} catch (InsufficientBalanceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			case 5:
				System.out.print("Enter source mobile number: ");
				String sourceMobile = sc.next();
				
				System.out.print("Enter target mobile number: ");
				String targetMobile = sc.next();
				
				System.out.println("Enter amount to be transferred");
				amount = sc.nextBigDecimal();
				
				try 
				{
					customer = service.fundTransfer(sourceMobile, targetMobile, amount);
					System.out.println("Amount has successfully transferred from account " + customer.getName());
					System.out.println("And now your balance is " + customer.getWallet().getBalance());

				} 
				catch (InvalidInputException e) {
					e.printStackTrace();
				} catch (InsufficientBalanceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				break;
				
			case 6:
				System.out.println("Enter mobile number to view transactions");
				String mobileNo = sc.next();
				
				List<Transactions> list = new ArrayList<>();
			try {
				list = service.getTransactions(mobileNo);
				
				Iterator<Transactions> it = list.iterator();
				
				System.out.println("MobileNo\t Transaction Type Amount\t");
				
				while(it.hasNext()) {
					Transactions transaction = it.next();
					System.out.print(transaction.getMobileNo()+"\t");
					System.out.print(transaction.getTransactionType()+"\t");
					System.out.print(transaction.getAmount());
					System.out.println();
				}
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}
				break;

			case 0:
				System.out.println("Thank you for using our services");
				System.out.println("Good Bye");
				System.exit(0);
			
			default:
				System.out.println("Please enter valid choice");
				break;
		}
		
		
	}
	
	public static void main(String[] args) 
	{
		Client client = new Client();
		while(true) {
			client.menu();
		}
	}
	
	
}
