package service;

import model.*;
import repository.EventRepository;
import repository.FineRepository;
import repository.VehicleRepository;

import java.util.*;

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

    public List<Vehicle> sortByOwnerCity() {
        return vehicleRepository.getAllVehicles().stream().sorted(Comparator.comparing(Vehicle::getOwnerCity).reversed().thenComparingInt(Vehicle::getId).reversed()).toList();
    }

    public void saveVehicles() {
        vehicleRepository.saveVehicles(sortByOwnerCity());
    }

    public int calculateRiskScore(Event e) {
        return switch (e.getType()) {
            case SPEEDING -> e.getSeverity() * 2;
            case RED_LIGHT -> e.getSeverity() * 3;
            case ACCIDENT -> e.getSeverity() * 5;
            case PRIORITY_PASS -> e.getSeverity() * 1;
        };
    }

    public List<Map.Entry<Vehicle, Integer>> getTotalRisks(List<Vehicle> vehicles, List<Event> events, List<Fine> fines) {
        Map<Vehicle, Integer> totalScores = new HashMap<>();

        Map<Integer, Integer> eventScore = new HashMap<>();
        for (Event e : events) {
            eventScore.merge(e.getVehicleId(), calculateRiskScore(e), Integer::sum);
        }

        Map<Integer, Integer> fineAmounts = new HashMap<>();
        for (Fine f : fines) {
            fineAmounts.merge(f.getVehicleId(), f.getAmount(), Integer::sum);
        }

        for (Vehicle v : vehicles) {
            int score = eventScore.getOrDefault(v.getId(), 0) - fineAmounts.getOrDefault(v.getId(), 0);

            totalScores.put(v, score);
        }

        return totalScores.entrySet().stream().sorted(Map.Entry.<Vehicle, Integer>comparingByValue()).limit(5).toList();

    }

    public void calculateReport() {
        Map<EventType, Integer> count = new HashMap<>();
        List<Event> events = eventRepository.getAllEvents();

        for (Event e : events) {
            count.put(e.getType(), count.getOrDefault(e.getType(), 0) + 1);
        }

        Map<EventType, Integer> sortedCount = count.entrySet().stream().sorted(Map.Entry.<EventType, Integer>comparingByValue().reversed())
                .collect(
                        LinkedHashMap::new,
                        (m, e) -> m.put(e.getKey(), e.getValue()),
                        LinkedHashMap::putAll
                );

        eventRepository.saveReport(sortedCount);
    }

}
