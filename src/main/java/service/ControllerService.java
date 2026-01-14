package service;

import model.*;
import repository.EventRepository;
import repository.FineRepository;
import repository.VehicleRepository;

import java.util.List;

public class ControllerService {

    private final VehicleRepository vehicleRepository;
    private final EventRepository eventRepository;
    private final FineRepository fineRepository;

    public ControllerService(VehicleRepository vehicleRepository, EventRepository eventRepository, FineRepository fineRepository) {
        this.vehicleRepository = vehicleRepository;
        this.eventRepository = eventRepository;
        this.fineRepository = fineRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.getAllVehicles();
    }

    public List<Event> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    public List<Fine> getAllFines() {
        return fineRepository.getAllFines();
    }

    public List<Vehicle> filterByVehicleTypeAndStatus(VehicleType type, VehicleStatus status) {
        return vehicleRepository.getAllVehicles().stream().filter(v -> v.getType().equals(type) && v.getStatus().equals(status)).toList();
    }

}
