package io.reactivex.lab.services.common;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.ObjectWriter;

import java.io.IOException;
import java.util.Map;

public class SimpleJson {

    private static final SimpleJson INSTANCE = new SimpleJson();

    private SimpleJson() {

    }

    public static Map<String, Object> jsonToMap(String jsonString) {
        return INSTANCE._jsonToMap(jsonString);
    }

    public static String mapToJson(Map<String, ?> map) {
        return INSTANCE._mapToJson(map);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectReader objectReader = objectMapper.reader(Map.class);

    private Map<String, Object> _jsonToMap(String jsonString) {
        try {
            return objectReader.readValue(jsonString);
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse JSON", e);
        }
    }

    private final ObjectWriter objectWriter = objectMapper.writerWithType(Map.class);

    private String _mapToJson(Map<String, ?> map) {
        try {
            return objectWriter.writeValueAsString(map);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write JSON", e);
        }
    }
}
