package org.jsp.BankCus.controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jsp.BankCus.dao.BankDao;
import org.jsp.BankCus.dao.CustDao;
import org.jsp.BankCus.dto.Bank;
import org.jsp.BankCus.dto.Customer;

public class Test {
	static Scanner sc=new Scanner(System.in);
	static BankDao bdao=new BankDao();
	static CustDao cdao=new CustDao();
	
	static EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	static EntityTransaction transaction=manager.getTransaction();
	
	public static void main(String[] args) {
		System.out.println("1:save the bank");
		System.out.println("2:update the bank");
		System.out.println("3:find the bank by id");
		System.out.println("4:save the Customer");
		System.out.println("5:update the customer");
		System.out.println("6:find the customer by bank id");
		System.out.println("7:find the customer by bank name");
		System.out.println("8:delete the customer");
		
		int choice =sc.nextInt();
		switch(choice) {
		
		case 1:{
			saveBank();
			break;
		}
		case 2:{
			updateBank();
			break;
			
		}
		case 3:{
			findBankById();
			break;
		}
		case 4:{
			saveCustomer();
			break;
		}
		case 5:{
			updateCustomer();
			break;
		}
		case 6:{
			findCustByBankId();
			break;
		}
		case 7:{
			findCustByBankName();
			break;
		}
		case 8:{
			deleteCustomer();
			break;
		}
		}
	}

	public static void deleteCustomer() {
		System.out.println("enter bank id to delete");
		int bid=sc.nextInt();
		System.out.println("enter customer id to delete");
		int cid=sc.nextInt();
		cdao.deleteCustomer(bid, cid);
		
	}

	public static void findCustByBankName() {
		System.out.println("enter name to find");
		String name=sc.next();
		List<Customer> list=cdao.findCustomerByBankName(name);
		if(list.size()>0) {
			for(Customer c:list) {
				System.out.println(c.getName());
			}
		}
		
	}

	public static void findCustByBankId() {
		System.out.println("enter id to find");
		int id=sc.nextInt();
		List<Customer> list=cdao.findCustomerByBankId(id);
		if(list.size()>0) {
			for(Customer c:list) {
				System.out.println(c.getName());
				
			}
		}
		
	}

	public static void updateCustomer() {
		System.out.println("enter id to find");
		int id=sc.nextInt();
		Bank b=manager.find(Bank.class, id );
		Bank bi=bdao.findBankById(id);
		if(b!=null) {
			System.out.println("enter id,name,gender,phone,email");
			int cid=sc.nextInt();
			String name=sc.next();
			String gender=sc.next();
			long phone=sc.nextLong();
			String email=sc.next();
			Customer c=new Customer();
			c.setId(cid);
			c.setName(name);
			c.setGender(gender);
			c.setPhone(phone);
			c.setEmail(email);
			Customer cust=cdao.updateCust(c, id);
			if(cust!=null)
				System.out.println("customer updated with id:"+cust.getId()+"with bank id"+b.getId());
			else
				System.out.println("Bank not found");
			
		}
		
		
	}

	public static void saveCustomer() {
		System.out.println("enter id to find");
		int id=sc.nextInt();
		Bank b=manager.find(Bank.class, id );
		if(b!=null) {
			System.out.println("enter name,gender,phone,email");
			String name=sc.next();
			String gender=sc.next();
			long phone=sc.nextLong();
			String email=sc.next();
			Customer c=new Customer();
			c.setName(name);
			c.setGender(gender);
			c.setPhone(phone);
			c.setEmail(email);
			Customer cust=cdao.saveCustomer(c, id);
			if(cust!=null)
				System.out.println("customer updated with id:"+cust.getId()+"with bank id"+b.getId());
			else
				System.out.println("Bank not found");
			
		}
		
	}

	public static void findBankById() {
		System.out.println("enter id to find the bank");
		int id=sc.nextInt();
		Bank b=new Bank();
		b=bdao.findBankById(id);
		System.out.println("the bank is:"+b.getName());
		
	}

	public static void updateBank() {
		System.out.println("enter id,name and branch to update the details");
		int id=sc.nextInt();
		String name=sc.next();
		String branch=sc.next();
		Bank b=new Bank();
		b.setId(id);
		b.setName(name);
		b.setBranch(branch);
		b=bdao.updateBank(b);
		System.out.println("bank updated with id:"+b.getId());
		
	}

	public static void saveBank() {
		System.out.println("enter name and branch to save the details");
		String name=sc.next();
		String branch=sc.next();
		Bank b=new Bank();
		b.setName(name);
		b.setBranch(branch);
		b=bdao.saveBank(b);
		System.out.println("bank saved with id:"+b.getId());
		
	}

}
