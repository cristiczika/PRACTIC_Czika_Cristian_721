package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Event;
import model.EventType;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventRepository {

    private final String filePath;
    private final ObjectMapper objectMapper;

    public EventRepository(String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
    }

    public List<Event> getAllEvents() {
        try {
            return objectMapper.readValue(
                    new File(filePath),
                    new TypeReference<List<Event>>() {}
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveReport(Map<EventType, Integer> report) {
        try (FileWriter writer = new FileWriter("src/data/traffic_report.txt")) {

            for (Map.Entry<EventType, Integer> entry : report.entrySet()) {
                writer.write(entry.getKey() + " -> " + entry.getValue() + "\n");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
