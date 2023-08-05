package org.jsp.BankCus.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.BankCus.dto.Bank;
import org.jsp.BankCus.dto.Customer;

public class CustDao {

	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	
	public Customer saveCustomer(Customer c,int b_id) {
		Bank b=manager.find(Bank.class, b_id);
		if(b!=null) {
			b.getCustomers().add(c);
			c.setBank(b);
			manager.persist(c);
			transaction.begin();
			transaction.commit();
			return c;
		}
		return null;
	}
	
	public Customer updateCust(Customer c,int b_id) {
		Bank b=manager.find(Bank.class, b_id);
		if(b!=null) {
			b.getCustomers().add(c);
			c.setBank(b);
			manager.merge(c);
			transaction.begin();
			transaction.commit();
			return c;
		}
		return null;
	}
	
	public List<Customer> findCustomerByBankId(int b_id){
		Query q=manager.createQuery("select b.customers from Bank b where b.id=?1");
		q.setParameter(1, b_id);
		return q.getResultList();
	}
	
	public List<Customer> findCustomerByBankName(String name){
		Query q=manager.createQuery("select b.customers from Bank b where b.name=?1");
		q.setParameter(1, name);
		return q.getResultList();
	}
	
	
	public void deleteCustomer(int b_id,int c_id) {
		Bank b=manager.find(Bank.class, b_id);
		if(b!=null) {
			Customer c=manager.find(Customer.class, c_id);
			if(c!=null) {
				c.setBank(null);
				manager.remove(c);
				transaction.begin();
				transaction.commit();
				System.out.println("customer deleted successfully");
			}
			else {
				System.out.println("bank not found");
			}
		}
	}
	
	
}
