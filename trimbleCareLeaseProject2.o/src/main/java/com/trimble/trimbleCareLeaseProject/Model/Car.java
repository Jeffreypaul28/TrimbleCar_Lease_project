package com.trimble.trimbleCareLeaseProject.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Car")
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    private String ownerId;

    private String location;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Lease> leaseHistory;

    public enum CarStatus {
        IDEAL, ON_LEASE, ON_SERVICE
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public CarStatus getStatus() {
		return status;
	}

	public void setStatus(CarStatus status) {
		this.status = status;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Lease> getLeaseHistory() {
		return leaseHistory;
	}

	public void setLeaseHistory(List<Lease> leaseHistory) {
		this.leaseHistory = leaseHistory;
	}

	public Car() {
	}

	

	
    
    
}
