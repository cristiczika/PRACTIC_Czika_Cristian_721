package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Vehicle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {

    private final String filePath;
    private final ObjectMapper objectMapper;

    public VehicleRepository(String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
    }

    public List<Vehicle> getAllVehicles() {
        try {
            return objectMapper.readValue(
                    new File(filePath),
                    new TypeReference<List<Vehicle>>() {}
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
