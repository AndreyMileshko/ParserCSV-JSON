import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserCSV extends Parsers {

    protected static void parseCSV(String fileName, String resultFile) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        List<Employee> result = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();
            result = csv.parse();
        } catch (IOException e) {
            System.out.println("При создании списка Employee выброшено исключение.");
        }
        String json = listToJson(result);
        writeString(formatJSON(json), resultFile);
    }
}
