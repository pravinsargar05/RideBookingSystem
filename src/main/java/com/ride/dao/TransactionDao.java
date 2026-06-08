package com.ride.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ride.entity.Driver;
import com.ride.entity.Rides;
import com.ride.entity.Transaction;
import com.ride.entity.Users;
import com.ride.enums.DriverStatus;
import com.ride.enums.RideStatus;
import com.ride.enums.TransactionStatus;
import com.ride.enums.TransactionType;
import com.ride.main.Connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class TransactionDao {
	static EntityManager em = Connection.getEntityManagerFactory().createEntityManager();
	Transaction tran = new Transaction();
	Rides ride = new Rides();
	Driver dr = new Driver();
	public void insertCash(long id) {
		Users users = em.find(Users.class, id);
		if(users!=null) {
			tran.setUsers(users);
			tran.setType(TransactionType.CASH);
			tran.setStatus(TransactionStatus.COMPLETED);
			em.getTransaction().begin();
			em.persist(tran);
			em.getTransaction().commit();
			System.out.println(" Cash Transaction Completed..");
		}else {
			System.out.println("Data not found..");
		}
	}
	public void insertCard(long id) {

	    Users users = em.find(Users.class, id);

	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Rides> query = cb.createQuery(Rides.class);
	    Root<Rides> root = query.from(Rides.class);

	    query.select(root)
	         .where(cb.equal(root.get("users").get("id"), users.getId()))
	         .orderBy(cb.desc(root.get("id")));

	    TypedQuery<Rides> query2 = em.createQuery(query);
	    Rides ride = query2.getSingleResult();


	    if (ride!=null) {

	        tran.setUsers(users);
	        tran.setType(TransactionType.CARD);
	        tran.setStatus(TransactionStatus.COMPLETED);

	        ride.setStatus(RideStatus.COMPLETED);
	        Driver driver = ride.getDriver();
	        driver.setDriverStatus(DriverStatus.AVALIBLE);

	        em.getTransaction().begin();

	        em.merge(driver); // update existing driver
	        em.merge(ride);   // update existing ride
	        em.persist(tran); // insert new transaction only
	        em.getTransaction().commit();

	        System.out.println("Card Transaction Completed..");

	    } else {
	        System.out.println("Data not found..");
	    }
	}
	public void insertUpi(long id) {
		Users users = em.find(Users.class, id);
		
		if(users!=null) {
			tran.setUsers(users);
			tran.setType(TransactionType.UPI);
			tran.setStatus(TransactionStatus.COMPLETED);
			ride.setStatus(RideStatus.COMPLETED);
			dr.setDriverStatus(DriverStatus.AVALIBLE);
			em.getTransaction().begin();
			em.persist(tran);
			em.merge(ride);
			em.merge(dr);
			em.getTransaction().commit();
			System.out.println(" UPI Transaction Completed..");
		}else {
			System.out.println("Data not found..");
		}
	}
	
	public void cancelTran(long id) {
		Users users = em.find(Users.class, id);
		if(users!=null) {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
			Root<Transaction> from = query.from(Transaction.class);
			query.select(from).where(cb.equal(from.get("users").get("id"), id)).orderBy(cb.desc(from.get("id")));
				TypedQuery<Transaction> query2 = em.createQuery(query);
				query2.setMaxResults(1);
				Transaction tr = query2.getSingleResult();
				tr.setStatus(TransactionStatus.CANCEL);
			if(query2!=null) {
				em.getTransaction().begin();
				em.merge(tr);
				em.getTransaction().commit();
				System.out.println("cancel successfulll!!");
			}else {
				System.out.println("User not found");
			}
		}
	}
	public List<Transaction> fetchByUser(long id){
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
			Root<Transaction> root = query.from(Transaction.class);
			query.select(root).where(cb.equal(root.get("users").get("id"), id));
			TypedQuery<Transaction> query2 = em.createQuery(query);
			
			List<Transaction> resultList = query2.getResultList();
			if(resultList.isEmpty()) {
				System.out.println("Data not Found...");
				
			}
				return resultList;
			
		}
		
	public List<Transaction> fetchByRide(long id){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root).where(cb.equal(root.get("users").get("rides").get("id"), id));
		TypedQuery<Transaction> query2 = em.createQuery(query);
		List<Transaction> list = query2.getResultList();
		if(list.isEmpty()) {
			System.out.println("Data not found...");
		}
		return list;
	}
		
	
	public List<Users>  fetchPendding() {
		TypedQuery<Transaction> query2 =
		        em.createQuery("select t from Transaction t", Transaction.class);

		List<Transaction> resultList = query2.getResultList();

		Set<Long> userIds = new HashSet<Long>();

		for (Transaction t : resultList) {
		    userIds.add(t.getUsers().getId());   
		}

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Users> query = cb.createQuery(Users.class);
		Root<Users> root = query.from(Users.class);

		query.select(root);

		if (!userIds.isEmpty()) {
		    query.where(cb.not(root.get("id").in(userIds)));
		}

		  TypedQuery<Users> query3 = em.createQuery(query);
		  query3.setFirstResult(0*5);
			query3.setMaxResults(2);
		  return query3.getResultList();
		
	}
	
	public List<Transaction> fetchCancel() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root).where(cb.equal(root.get("status"), TransactionStatus.CANCEL));
		TypedQuery<Transaction> query2 = em.createQuery(query);
		return query2.getResultList();
	}
	
	public List<Transaction> fetchComplete(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root).where(cb.equal(root.get("status"), TransactionStatus.COMPLETED));
		TypedQuery<Transaction> query2 = em.createQuery(query);
		query2.setFirstResult(0*2);
		query2.setMaxResults(2);
		return query2.getResultList();
	}
}
