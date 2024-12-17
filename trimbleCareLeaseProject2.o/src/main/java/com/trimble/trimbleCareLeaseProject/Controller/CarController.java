package com.trimble.trimbleCareLeaseProject.Controller;

import com.trimble.trimbleCareLeaseProject.DTO.CarDTO;
import com.trimble.trimbleCareLeaseProject.ServiceImpl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
@CrossOrigin(origins = "*") // Allow cross-origin requests
public class CarController {

    @Autowired
    private CarServiceImpl carService;

    // Add a new car
    @PostMapping("/add")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO) {
        CarDTO addedCar = carService.addCar(carDTO);
        return ResponseEntity.ok(addedCar);
    }

    // Get all cars
    @GetMapping("/all")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    // Get a car by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<CarDTO>> getCarById(@PathVariable Long id) {
        Optional<CarDTO> car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    // Update a car by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @RequestBody CarDTO updatedCarDTO) {
        try {
            CarDTO updatedCar = carService.updateCar(id, updatedCarDTO);
            return ResponseEntity.ok(updatedCar);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null); // Or return a custom error message
        }
    }

    // Delete a car by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
