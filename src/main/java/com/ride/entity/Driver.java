package com.ride.entity;

import java.util.List;

import com.ride.enums.DriverStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;

@Entity
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	@Email(message = "Enter valid Email ")
	private String email;
	private long phone;
	private double latitude;
	private double longitude;
	
	@Enumerated(EnumType.STRING)
	private DriverStatus driverStatus;
	
	@OneToMany(mappedBy = "driver",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Rides> rides;
	
	@OneToOne(mappedBy = "driver", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Vehicle vehicle;

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

	public List<Rides> getRides() {
		return rides;
	}

	public void setRides(List<Rides> rides) {
		this.rides = rides;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public DriverStatus getDriverStatus() {
		return driverStatus;
	}

	public void setDriverStatus(DriverStatus driverStatus) {
		this.driverStatus = driverStatus;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
	
}
