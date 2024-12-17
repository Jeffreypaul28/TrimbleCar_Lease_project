package com.trimble.trimbleCareLeaseProject.DTO;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LeaseDTO {

    private Long id;
    private Long carId;
    private String carModel;
    private Long customerId;
    private String customerName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    

    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getCarId() {
		return carId;
	}



	public void setCarId(Long carId) {
		this.carId = carId;
	}



	public String getCarModel() {
		return carModel;
	}



	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}



	public Long getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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



	public LeaseDTO(Long id, Long carId, String carModel, Long customerId, String customerName, 
                    LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.carId = carId;
        this.carModel = carModel;
        this.customerId = customerId;
        this.customerName = customerName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
