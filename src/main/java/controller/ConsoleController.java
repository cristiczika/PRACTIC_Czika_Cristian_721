package controller;

import model.*;
import service.ControllerService;

import java.util.List;
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

}
