package com.ilp.entity;

import java.util.ArrayList;

public class Customer{
	
	private String customerId;
    private String customerName;
    private ArrayList<Account> accounts;
    
    //constructor and methods for Customer Class
	
    public Customer(String customerId, String customerName, ArrayList<Account> accounts) {
		
		this.customerId = customerId;
		this.customerName = customerName;
		this.accounts = accounts;
	}
	
    
    
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

}
