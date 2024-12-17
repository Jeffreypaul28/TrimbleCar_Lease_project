package com.trimble.trimbleCareLeaseProject.ServiceImpl;

import com.trimble.trimbleCareLeaseProject.DTO.CarDTO;
import com.trimble.trimbleCareLeaseProject.Model.Car;
import com.trimble.trimbleCareLeaseProject.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl {

    @Autowired
    private CarRepository carRepository;

    public CarDTO addCar(CarDTO carDTO) {
        Car car = mapToEntity(carDTO);
        Car savedCar = carRepository.save(car);
        return mapToDTO(savedCar);
    }

    public List<CarDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<CarDTO> getCarById(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        return carOptional.map(this::mapToDTO);
    }

    public CarDTO updateCar(Long id, CarDTO updatedCarDTO) {
        return carRepository.findById(id).map(existingCar -> {
            existingCar.setModel(updatedCarDTO.getModel());
            existingCar.setStatus(Car.CarStatus.valueOf(updatedCarDTO.getStatus())); // Convert String to Enum
            existingCar.setOwnerId(updatedCarDTO.getOwnerId());
            existingCar.setLocation(updatedCarDTO.getLocation());

            Car updatedCar = carRepository.save(existingCar);
            return mapToDTO(updatedCar);
        }).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    // Mapping methods
    private CarDTO mapToDTO(Car car) {
        return new CarDTO(
                car.getId(),
                car.getModel(),
                car.getStatus().toString(),
                car.getOwnerId(),
                car.getLocation()
        );
    }

    private Car mapToEntity(CarDTO carDTO) {
        Car car = new Car();
        car.setModel(carDTO.getModel());
        car.setStatus(Car.CarStatus.valueOf(carDTO.getStatus())); // Convert String to Enum
        car.setOwnerId(carDTO.getOwnerId());
        car.setLocation(carDTO.getLocation());
        return car;
    }
}
