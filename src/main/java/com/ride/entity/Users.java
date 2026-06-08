package com.ride.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@Email(message = "Enter valid email")
	private String email;
	private long phone;
	@PositiveOrZero(message = "Balance is does not negative")
	private double wallet_balancd;
	
	public double getWallet_balancd() {
		return wallet_balancd;
	}

	public void setWallet_balancd(double wallet_balancd) {
		this.wallet_balancd = wallet_balancd;
	}

	@OneToMany(mappedBy = "users",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<Transaction> transaction;
	
	@OneToMany(mappedBy = "users",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<Rides> rides;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public List<Rides> getRides() {
		return rides;
	}

	public void setRides(List<Rides> rides) {
		this.rides = rides;
	}
	
	
}
