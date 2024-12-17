package com.trimble.trimbleCareLeaseProject.Repository;

import com.trimble.trimbleCareLeaseProject.Model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LeaseRepository extends JpaRepository<Lease, Long> {
    List<Lease> findByCustomerId(Long customerId);
    List<Lease> findByCarId(Long carId);
    long countByCustomerIdAndEndDateAfter(Long customerId, LocalDateTime currentDateTime);
}
