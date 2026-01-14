package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Event;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

}
