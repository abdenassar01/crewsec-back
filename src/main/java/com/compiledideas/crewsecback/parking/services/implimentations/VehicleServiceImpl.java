package com.compiledideas.crewsecback.parking.services.implimentations;

import com.compiledideas.crewsecback.exceptions.ResourceNotFoundException;
import com.compiledideas.crewsecback.parking.models.Vehicle;
import com.compiledideas.crewsecback.parking.repositories.ParkingRepository;
import com.compiledideas.crewsecback.parking.repositories.VehicleRepository;
import com.compiledideas.crewsecback.parking.services.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("vehicle_jpa_service")
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository repository;
    private final ParkingRepository parkingRepository;

    @Override
    public Page<Vehicle> findAllVehicles(Integer page, Integer limit) {
        return repository.findAll(PageRequest.of(page, limit));
    }

    @Override
    public Page<Vehicle> findAllVehiclesByParking(Long parkingId, Integer page, Integer limit) {
        var parking = parkingRepository.findById(parkingId).orElseThrow(() -> new ResourceNotFoundException("parking", "id", parkingId));
        return repository.findAllByParking(parking, PageRequest.of(page, limit));
    }

    @Override
    public Vehicle findVehicleById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("vehicle", "id", id));
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        vehicle.setId(id);
        return repository.save(vehicle);
    }

    @Override
    public Vehicle deleteVehicle(Long id) {
        var vehicle = findVehicleById(id);
        repository.deleteById(id);
        return vehicle;
    }
}
