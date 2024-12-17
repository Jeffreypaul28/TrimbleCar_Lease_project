package com.trimble.trimbleCareLeaseProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private Long id;
    private String model;
    private String status; // Representing CarStatus as a String
    private String ownerId;
    private String location;
    
    
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
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


	public CarDTO(Long id, String model, String status, String ownerId, String location) {
		super();
		this.id = id;
		this.model = model;
		this.status = status;
		this.ownerId = ownerId;
		this.location = location;
	}
    
    
}
