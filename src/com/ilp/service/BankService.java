package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class BankService {

	public static Service createService() {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("\r\n Available services are :\r\n 1.Cash Deposit \r\n 2.OnlineBanking \r\n 3.ATMWithdrawl \r\n 4.MobileBanking \r\nEnter your choice\n");
		scanner.nextLine();
		System.out.println("\nService Code : - ");
		String serviceCode = scanner.nextLine();
		System.out.println("\nService Name : - ");
		String serviceName = scanner.nextLine();
		System.out.println("\nService rate : - ");
		double rate = scanner.nextDouble();
		Service service = new Service(serviceCode,serviceName,rate);
		return service;
	}

	
	
	
	
	
	public static Product createProduct(ArrayList<Service> serviceList) {
		ArrayList<Service> defaultServiceList = new ArrayList<Service>();
		Scanner scanner = new Scanner(System.in);
		Product product = null;

		char sChoice='y';

		do {
			
			System.out.println("\nThe available services are\r\n");
	
			for(Service service: serviceList) {
				System.out.println("\n Service code: "+service.getServiceCode()+" \nService Name: "+service.getServiceName()+" \nService rate: Rs "+service.getRate()+" \n");
			}
			System.out.println("\nEnter the code of the service you want\r\n");
			String userChoice=scanner.nextLine();
			for(Service service:serviceList) {
				if(userChoice.equals(service.getServiceCode())  )
					defaultServiceList.add(service);
				
			}
			System.out.println("\nDo you want to add more services?(y/n)");
			
			
			sChoice=scanner.nextLine().charAt(0);
			
			
	    }while(sChoice=='y'||sChoice=='Y');
		for(Service services:defaultServiceList) {
			System.out.println("\nThe services you chose are: \nserivice code:"+services.getServiceCode()+"\nService name:"+services.getServiceName()+"\nService rate: "+services.getRate());
		}
		System.out.println("\n1.Savings Max Account \r\n"+ "\r\n"+ "2.Current Account  \r\n"+ "\r\n"+ "3.Loan Account"+"\n\r\n Enter the product you want\r\n");
		int userChoice=scanner.nextInt();
		System.out.println("\nProduct Code : - ");
		String productCode = scanner.nextLine();
		scanner.nextLine();
		System.out.println("\nProduct name : - ");
		String productName = scanner.nextLine();
		if(userChoice==1)
		{
			SavingsMaxAccount savingsMaxAccount = new SavingsMaxAccount(productCode, productName, defaultServiceList);
			System.out.println("\nSavings Max Account Created\n");
			product = savingsMaxAccount;
		}
		else if (userChoice==2)
		{
			CurrentAccount currentAccount = new CurrentAccount(productCode, productName, defaultServiceList);
			System.out.println("\nCurrent Account Created\n");
			product = currentAccount;
		}
		else if (userChoice==3)
		{
			LoanAccount loanAccount = new LoanAccount(productCode, productName, defaultServiceList);
			System.out.println("\nLoans Account Created\n");
			product = loanAccount;
		}

		return product;
	}
	
	
	

	public static Customer createCustomer(ArrayList<Product> productList) {
		Scanner scanner = new Scanner(System.in);
		for (Product products:productList) {
		
		System.out.println(products);
		}
		System.out.println("\nEnter customer name:");
		String customerName = scanner.nextLine();
		
		System.out.println("\nEnter customer ID:");
		String customerId = scanner.nextLine();
		
		char customerChoice = 'y';
		ArrayList<Account> accountList = new ArrayList<Account>() ;
		Product product = new Product("","",null);
			do {
				System.out.println("\nEnter account no:");
				String accountNo = scanner.nextLine();
				System.out.println("\nEnter account balance:");
				double accountBalance = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("\nSelect the name of Account type you want:\n 1.Savings Max Account\n 2.Current Account\n 3.Loan Account\n");
				String accountType = scanner.nextLine();
				String productName = accountType;
			
			for(Product productitem :productList) 
			{
					if(productName.equalsIgnoreCase(productitem.getProductName())) {
						product = productitem;
						System.out.println("product added");
					}
			}
			Account account = new Account(accountNo, accountType, accountBalance, product);
			accountList.add(account);
			

			System.out.println("\nDo you want to add more accounts?(y/n)");
			customerChoice=scanner.nextLine().charAt(0);
			
		}while(customerChoice=='y'||customerChoice=='Y');
		
		Customer customer = new Customer(customerId, customerName, accountList);
		
		for(Account account:customer.getAccounts()) {
			System.out.println("\nName of product you chose:"+account.getProduct().getProductName());
			for(Service service : account.getProduct().getserviceList()) {
				System.out.println("\nServices you chose are\n"+service.getServiceName());
			}
		}
		return customer;
		}


	public static void manageAccounts(Customer customer) {

		Scanner scanner=new Scanner(System.in);
		char continuev = 0;
		int userChoice=0;
		System.out.println("\nEnter the customer id:");
		String customerId = scanner.nextLine();
		if(customerId.equalsIgnoreCase(customer.getCustomerId())) {
			System.out.println("\nCustomer name: "+customer.getCustomerName());
			for(Account account:customer.getAccounts()) {
				System.out.println("\nAccount name:"+account.getAccountType());
				System.out.println("\nEnter the account code of the account to which you want the details of");
				String accountCode=scanner.nextLine();
				do {
					System.out.println("\nEnter what you want to do: \n 1.Deposit Money \n2.Withdraw Money \n3.Check Balance");
					userChoice=scanner.nextInt();
					switch(userChoice) {
					case 1:	  
						System.out.println("\nEnter the deposit money:");
						double depositMoney = scanner.nextDouble();
						for(Account accounts:customer.getAccounts()) 
						{
							if(accounts.getProduct().getProductCode().equalsIgnoreCase(accountCode))
							{
								if(accounts.getProduct() instanceof LoanAccount) 
								{
									LoanAccount loanAccount = (LoanAccount) account.getProduct();
									System.out.println("\nDo you want to deposit through \n1.cash \n2.cheque? ");
									int choice = scanner.nextInt();
									switch(choice)
									{
									case 1: System.out.println("\nYou selected cash deposit");
										break;
									case 2:	depositMoney=depositMoney - depositMoney*loanAccount.getChequeDeposit();
										break;
									}

								}
								
								account.setBalance(account.getBalance()+depositMoney);
							}						
						}
						break;
					case 2:
						System.out.println("\nEnter the amount you want to withdraw:");
						double withdrawMoney = scanner.nextDouble();
						for(Account accounts:customer.getAccounts()) 
						{
							if(accounts.getProduct().getProductCode().equalsIgnoreCase(accountCode)) 
							{
								if(accounts.getProduct() instanceof SavingsMaxAccount) 
								{
									SavingsMaxAccount savingsMaxAccount = (SavingsMaxAccount) account.getProduct();
									if(account.getBalance()-withdrawMoney <= savingsMaxAccount.getMinimumBalance())
									{
										System.out.println("\nYou have insufficiant account balance");
									}
								}
								else
								{
									System.out.println("\nMoney withdrawed!!");
									account.setBalance(account.getBalance()- withdrawMoney);
									System.out.println("\nYour account balance is :"+account.getBalance());
								}
							}
						}
						
						break;
					case 3:
						for(Account accounts:customer.getAccounts()) 
						{
							System.out.println("\nDetails of all accounts:\n\n\r ");
							System.out.println("\nAccount number:"+accounts.getAccountNo()+"\nAccount Type:"+accounts.getAccountType()+"\nAccount Balance: "+accounts.getBalance());
							for(Service service:accounts.getProduct().getserviceList()) 
							{
								System.out.print("\nServices available are:");
								System.out.print("Service Code:"+service.getServiceCode()+"\nService Name:"+service.getServiceName()+"\nService Rate:"+service.getRate());

							}
						}
						break;
					default:
						System.out.println("You entered an invalid choice");
						break;
					}
				}while(continuev=='y'||continuev=='Y');
			}
			
		}
		
	else 
	{
		System.out.println("Invalid Customer");
	}
		
 }
	
}
	


