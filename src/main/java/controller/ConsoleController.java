package controller;

import model.Event;
import model.Fine;
import model.Vehicle;
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
    }

    private void subpunct1() {
        List<Vehicle> vehicles = controllerService.getAllVehicles();
        List<Event> events = controllerService.getAllEvents();
        List<Fine> fines = controllerService.getAllFines();

        System.out.println("Vehicles loaded: " + vehicles.size());
        System.out.println("Events loaded: " + events.size());
        System.out.println("Fines loaded: " + fines.size() + "\n");

        vehicles.forEach(System.out::println);
    }

}
