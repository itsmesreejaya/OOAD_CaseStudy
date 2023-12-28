package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;
import com.ilp.service.BankService;

public class BankUtility {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Service> serviceList = new ArrayList<Service>();
	     ArrayList<Product> productList = new ArrayList <Product>();
	     Customer customer = new Customer("","",null);
	     Scanner scanner = new Scanner(System.in);

			int mainMenuChoice =0;

		System.out.println("******Welcome To Bank************ \r\n ");
		do {

		System.out.println("\n1.Create Service \r\n"
				+ "\r\n"
				+ "2.Create Product \r\n"
				+ "\r\n"
				+ "3.Create Customer\r\n\n"
				+ "4.Manage Accounts \r\n"
				+"\r\n 5.Exit\n");
			System.out.println("Enter what you want to do: \r\n");
			
			mainMenuChoice = scanner.nextInt();
			switch(mainMenuChoice) {
			case 1: serviceList.add(BankService.createService());
					break;
			case 2: productList.add(BankService.createProduct(serviceList));
					break;
			case 3: customer =BankService.createCustomer(productList);
					break;
			case 4: BankService.manageAccounts(customer);
					break;
			case 5:
				break;
			default:
					break;
			
			}
		}while(mainMenuChoice!=5);
		
		
		
		
	}

}
