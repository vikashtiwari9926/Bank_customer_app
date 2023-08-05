package org.jsp.BankCus.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jsp.BankCus.dto.Bank;

public class BankDao {
	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	
	public Bank saveBank(Bank b) {
		manager.persist(b);
		transaction.begin();
		transaction.commit();
		return b;
	}
	public Bank updateBank(Bank b) {
		manager.merge(b);
		transaction.begin();
		transaction.commit();
		return b;
	}
	
	public Bank findBankById(int b_id) {
		return manager.find(Bank.class, b_id);
	}

}
