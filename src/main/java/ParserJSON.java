import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ParserJSON {

    protected static String readString(String fileName) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String jsonString;
            while ((jsonString = reader.readLine()) != null) {
                result.append(jsonString);
            }
        } catch (IOException exc) {
            System.out.printf("При чтении файла %S исключение.", fileName);
        }
        return result.toString();
    }

    protected static List<Employee> jsonToList(String jsonString) {
        List<Employee> result = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.readValue(jsonString, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println("исключение при создании списка Employee");
        }
        return result;
    }
}
