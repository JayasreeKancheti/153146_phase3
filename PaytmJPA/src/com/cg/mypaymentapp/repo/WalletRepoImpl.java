package com.cg.mypaymentapp.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.util.JPAUtil;

public class WalletRepoImpl implements WalletRepo{

	private EntityManager entityManager;
	
	public WalletRepoImpl() {
		entityManager = JPAUtil.getEntityManager();
	}
	
	@Override
	public boolean save(Customer customer) throws InvalidInputException {
		try {
			
 			entityManager.persist(customer);
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}

	@Override
	public Customer findOne(String mobileNo) throws InvalidInputException {
		Customer customer = entityManager.find(Customer.class, mobileNo);
		return customer;
	}

	@Override
	public void startTransaction() {
		
		entityManager.getTransaction().begin();
		
	}

	@Override
	public void commitTransaction() {
		
		entityManager.getTransaction().commit();
		
	}

	@Override
	public void update(Customer customer) {
		
		entityManager.merge(customer);
		
	}

	@Override
	public void addTransaction(Transactions transactions) {
		
		entityManager.persist(transactions);
		
	}

	@Override
	public List<Transactions> getAllTransactions(String mobileNumber) {
		List<Transactions> list = new ArrayList<>();
		
		String query = "Select t from Transactions t where mobileNo = :mob";
		list = entityManager.createQuery(query,Transactions.class).setParameter("mob", mobileNumber).getResultList();
		
//		for (Transactions transaction : list) {
//			if(transaction.getTransactionType().equals("deposit"))
//				history.add()
//			
//		}
		return list;
	}

}
