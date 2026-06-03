package com.ride.main;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connection {
	
	public static final EntityManagerFactory emf= Persistence.createEntityManagerFactory("pravin");
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

}
