package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Fine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FineRepository {

    private final String filePath;
    private final ObjectMapper objectMapper;

    public FineRepository(String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
    }

    public List<Fine> getAllFines() {
        try {
            return objectMapper.readValue(
                    new File(filePath),
                    new TypeReference<List<Fine>>() {}
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
