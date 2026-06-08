package com.ride.entity;

import com.ride.enums.TransactionStatus;
import com.ride.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	
	@Enumerated(EnumType.STRING)
	private TransactionStatus status;
	
	public TransactionStatus getStatus() {
		return status;
	}

	
	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
}
