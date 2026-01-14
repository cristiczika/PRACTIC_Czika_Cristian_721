package controller;

import model.*;
import service.ControllerService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleController {

    private final ControllerService controllerService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleController(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    public void run() {
        subpunct1();
        subpunct2();
        subpunct3();
        subpunct4();
        subpunct5();
        subpunct6();
        subpunct7();
    }

    private void subpunct1() {
        System.out.println("\n===== Unterpunkt 1 =====");
        List<Vehicle> vehicles = controllerService.getAllVehicles();
        List<Event> events = controllerService.getAllEvents();
        List<Fine> fines = controllerService.getAllFines();

        System.out.println("Vehicles loaded: " + vehicles.size());
        System.out.println("Events loaded: " + events.size());
        System.out.println("Fines loaded: " + fines.size() + "\n");

        vehicles.forEach(System.out::println);
    }

    private void subpunct2() {
        System.out.println("\n===== Unterpunkt 2 =====");
        System.out.print("Vehicle Type: ");
        VehicleType type = VehicleType.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Vehicle Status: ");
        VehicleStatus status = VehicleStatus.valueOf(scanner.nextLine().toUpperCase());

        controllerService.filterByVehicleTypeAndStatus(type, status).forEach(System.out::println);
    }

    private void subpunct3() {
        System.out.println("\n===== Unterpunkt 3 =====");
        controllerService.sortByOwnerCity().forEach(System.out::println);
    }

    private void subpunct4() {
        System.out.println("\n===== Unterpunkt 4 =====");
        controllerService.saveVehicles();
    }

    private void subpunct5() {
        System.out.println("\n===== Unterpunkt 5 =====");
        List<Event> events = controllerService.getAllEvents();

        for (int i = 0; i < 5; i++) {
            int riskScore = controllerService.calculateRiskScore(events.get(i));
            System.out.println("Event " + (i+1) + " -> severity=" + events.get(i).getSeverity() + " -> riskScore=" + riskScore);
        }
    }

    private void subpunct6() {
        System.out.println("\n===== Unterpunkt 6 =====");
        List<Vehicle> vehicles = controllerService.getAllVehicles();
        List<Event> events = controllerService.getAllEvents();
        List<Fine> fines = controllerService.getAllFines();

        List<Map.Entry<Vehicle, Integer>> ranking =
                controllerService.getTotalRisks(vehicles, events, fines);

        System.out.println("Top 5 Vehicles:");
        int pos = 1;
        for (var entry : ranking) {
            Vehicle v = entry.getKey();
            System.out.println(pos++ + ". " +
                    v.getLicensePlate() + " -> " + entry.getValue());
        }

        System.out.println("\nSafest vehicle: " + ranking.get(0).getKey().getLicensePlate() + " -> " + ranking.get(0).getValue());
    }

    private void subpunct7() {
        controllerService.calculateReport();
    }

}
