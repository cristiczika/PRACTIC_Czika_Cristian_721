import controller.ConsoleController;
import repository.EventRepository;
import repository.FineRepository;
import repository.VehicleRepository;
import service.CityService;

public class Main {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        VehicleRepository vehicleRepository = new VehicleRepository("src/data/vehicles.json");
        EventRepository eventRepository = new EventRepository("src/data/events.json");
        FineRepository fineRepository = new FineRepository("src/data/fines.json");
        CityService controllerService = new CityService(vehicleRepository, eventRepository, fineRepository);

        new ConsoleController(controllerService).run();
    }

}
