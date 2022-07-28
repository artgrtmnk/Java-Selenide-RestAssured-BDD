package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileReader {
    public String parseToken() {
        String token = "";
        try {
            Reader reader = Files.newBufferedReader(Paths.get("token.json"));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode parser = objectMapper.readTree(reader);
            token = parser.path("token").asText();

            reader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return token;
    }
}
