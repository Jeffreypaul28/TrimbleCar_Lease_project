package com.trimble.trimbleCareLeaseProject.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "User")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true)
	private long mobileNumber;

	private String email;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Lease> leaseHistory;

	public enum UserRole {
		CAR_OWNER, END_CUSTOMER, ADMIN
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public List<Lease> getLeaseHistory() {
		return leaseHistory;
	}

	public void setLeaseHistory(List<Lease> leaseHistory) {
		this.leaseHistory = leaseHistory;
	}

	public User() {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.role = role;
		this.leaseHistory = leaseHistory;
	}
}
