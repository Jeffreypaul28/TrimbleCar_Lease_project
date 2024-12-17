package com.trimble.trimbleCareLeaseProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trimble.trimbleCareLeaseProject.Model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
