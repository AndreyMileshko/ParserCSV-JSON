import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public abstract class Parsers {
    protected static String listToJson(List<Employee> list) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        return gson.toJson(list, listType);
    }

    protected static String formatJSON(String jsonStr) {           //написано с помощью stackoverflow
        String str = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(jsonStr, Object.class);
            str = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (Exception e) {
            System.out.println("При форматировании строки к виду JSON произошло исключение.");
        }
        return str;
    }

    protected static void writeString(String json, String resultFile) {
        try (FileWriter writer = new FileWriter(resultFile)) {
            writer.write(json);
        } catch (IOException exc) {
            System.out.println("Возникла исключительная ситуация при записи data.json.");
        }
    }
}
