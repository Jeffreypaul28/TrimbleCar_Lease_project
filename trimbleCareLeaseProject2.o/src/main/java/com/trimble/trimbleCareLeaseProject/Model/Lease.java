package com.trimble.trimbleCareLeaseProject.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Lease")
@Data
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Lease() {
		super();
		this.id = id;
		this.car = car;
		this.customer = customer;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	@ManyToOne
    @JoinColumn(name = "user_id")
    private User customer;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
    
    
    
}
