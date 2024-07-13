package part2.labwork3.sandox;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import java.io.*;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Test {

    public static void main(String[] args) {
        try {
            Document doc;
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            doc = db.parse(new File("Continent.xml"));
            System.out.println(Test.class.getName());
//            Node rootNode = doc.getDocumentElement();
//            mainLoop:
//            for (int i = 0; i < rootNode.getChildNodes().getLength(); i++) {
//                Node countriesNode = rootNode.getChildNodes().item(i);
//                if (countriesNode.getNodeName().equals("CountriesData")) {
//                    for (int j = 0; j < countriesNode.getChildNodes().getLength(); j++) {
//                        Node countryNode = countriesNode.getChildNodes().item(j);
//                        if (countryNode.getNodeName().equals("CountryData")) {
//                            // Знаходимо атрибут за іменем:
//                            if (countryNode.getAttributes().getNamedItem("Name").getNodeValue().equals("Франція")) {
//                                for (int k = 0; k < countryNode.getChildNodes().getLength(); k++) {
//                                    Node capitalNode = countryNode.getChildNodes().item(k);
//                                    if (capitalNode.getNodeName().equals("CapitalData")) {
//                                        capitalNode.getAttributes().getNamedItem("Name").setNodeValue("Париж");
//                                        break mainLoop;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            Transformer transformer = TransformerFactory.newInstance().newTransformer();
//            transformer.transform(new DOMSource(doc),
//                    new StreamResult(new FileOutputStream(new File("CorrectedConinent.xml"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (Stream<String> strings = Files.lines(Path.of("source.txt"))) {
            strings.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
