import java.util.List;

public class Main {
    public static void main(String[] args) {
        ParserCSV.parseCSV("data.csv", "data.json");
        ParserXML.parseXML("data.xml", "data2.json");
        ParserJSON.jsonToList(ParserJSON.readString("data.json")).forEach(System.out::println);
    }
}
