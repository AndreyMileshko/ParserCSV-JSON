import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserXML extends Parsers {

    protected static void parseXML(String fileName, String resultFile) {
        List<Employee> result;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(fileName);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("При получении объекта Document выброшено исключение.");
        }
        Node node = document.getDocumentElement();
        result = read(node);
        String json = ParserCSV.listToJson(result);
        writeString(formatJSON(json), resultFile);
    }

    private static List<Employee> read(Node node) {
        List<Employee> result = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                Element element = (Element) node_;
                String[] elementSplit = element.getTextContent().split("\n");
                Employee employee = new Employee(Integer.parseInt(elementSplit[1].trim()),
                        elementSplit[2].trim(),
                        elementSplit[3].trim(),
                        elementSplit[4].trim(),
                        Integer.parseInt(elementSplit[5].trim()));
                result.add(employee);
            }
        }
        return result;
    }
}
