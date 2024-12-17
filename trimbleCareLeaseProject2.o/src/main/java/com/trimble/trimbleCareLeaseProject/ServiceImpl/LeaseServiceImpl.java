package com.trimble.trimbleCareLeaseProject.ServiceImpl;

import com.trimble.trimbleCareLeaseProject.DTO.LeaseDTO;
import com.trimble.trimbleCareLeaseProject.Model.Car;
import com.trimble.trimbleCareLeaseProject.Model.Lease;
import com.trimble.trimbleCareLeaseProject.Model.User;
import com.trimble.trimbleCareLeaseProject.Repository.CarRepository;
import com.trimble.trimbleCareLeaseProject.Repository.LeaseRepository;
import com.trimble.trimbleCareLeaseProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaseServiceImpl {

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

//    public LeaseDTO startLease(Long carId, Long userId, LocalDateTime startDate, LocalDateTime endDate) {
//        // Fetch car and user details
//        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
//        User customer = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Check if the car is available
//        if (car.getStatus() != Car.CarStatus.IDEAL) {
//            throw new RuntimeException("Car not available for lease");
//        }
//
//        // Check if the customer already has 2 active leases
//        long activeLeaseCount = leaseRepository.countByCustomerIdAndEndDateAfter(userId, LocalDateTime.now());
//        if (activeLeaseCount >= 2) {
//            throw new RuntimeException("Customer cannot lease more than 2 cars at the same time");
//        }
//
//        // Create and save the new lease
//        Lease lease = new Lease();
//        lease.setCar(car);
//        lease.setCustomer(customer);
//        lease.setStartDate(startDate);
//        lease.setEndDate(endDate);
//
//        // Update car status to ON_LEASE
//        car.setStatus(Car.CarStatus.ON_LEASE);
//        carRepository.save(car);
//
//        Lease savedLease = leaseRepository.save(lease);
//
//        // Return LeaseDTO
//        return convertToLeaseDTO(savedLease);
//    }
    
    public LeaseDTO startLease(Long carId, Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        try {
            // Fetch car and user details
            Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car with ID " + carId + " not found"));
            User customer = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));

            // Check if the car is available
            if (car.getStatus() != Car.CarStatus.IDEAL) {
                throw new RuntimeException("Car with ID " + carId + " is not available for lease");
            }

            // Check if the customer already has 2 active leases
            long activeLeaseCount = leaseRepository.countByCustomerIdAndEndDateAfter(userId, LocalDateTime.now());
            if (activeLeaseCount >= 2) {
                throw new RuntimeException("Customer cannot lease more than 2 cars at the same time");
            }

            // Create and save the new lease
            Lease lease = new Lease();
            lease.setCar(car);
            lease.setCustomer(customer);
            lease.setStartDate(startDate);
            lease.setEndDate(endDate);

            // Update car status to ON_LEASE
            car.setStatus(Car.CarStatus.ON_LEASE);
            carRepository.save(car);

            Lease savedLease = leaseRepository.save(lease);

            return convertToLeaseDTO(savedLease);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error starting lease: " + e.getMessage());
            throw new RuntimeException("Failed to start lease: " + e.getMessage(), e);
        }
    }


    public LeaseDTO endLease(Long leaseId) {
        Lease lease = leaseRepository.findById(leaseId).orElseThrow(() -> new RuntimeException("Lease not found"));
        Car car = lease.getCar();

        // Update car status to IDEAL when lease ends
        car.setStatus(Car.CarStatus.IDEAL);
        carRepository.save(car);

        return convertToLeaseDTO(lease);
    }

    public List<LeaseDTO> getCarLeaseHistory(Long carId) {
        List<Lease> leases = leaseRepository.findByCarId(carId);
        return leases.stream()
                     .map(this::convertToLeaseDTO)
                     .collect(Collectors.toList());
    }

    public List<LeaseDTO> getCustomerLeaseHistory(Long customerId) {
        List<Lease> leases = leaseRepository.findByCustomerId(customerId);
        return leases.stream()
                     .map(this::convertToLeaseDTO)
                     .collect(Collectors.toList());
    }

    public LeaseDTO convertToLeaseDTO(Lease lease) {
        return new LeaseDTO(
            lease.getId(),
            lease.getCar().getId(),
            lease.getCar().getModel(),
            lease.getCustomer().getId(),
            lease.getCustomer().getUsername(),
            lease.getStartDate(),
            lease.getEndDate()
        );
    }
}
