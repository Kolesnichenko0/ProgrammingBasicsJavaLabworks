package part2.labwork3.sixth;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class StudentGroupDomProcessor {
    public void process(String inputXmlFilePath, String outputXmlFilePath, double increaseScoreBy) {
        try {
            Document document = readXmlDocument(inputXmlFilePath);
            updateScores(document, increaseScoreBy);
            writeXmlDocument(document, outputXmlFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Document readXmlDocument(String filePath) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(filePath));
    }

    private void updateScores(Document document, double increaseScoreBy) {
        NodeList nodeList = document.getElementsByTagName("RatingScoreData");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                double ratingScore = Double.parseDouble(element.getTextContent());
                ratingScore += increaseScoreBy;
                element.setTextContent(String.valueOf(ratingScore));
            }
        }
    }

    private void writeXmlDocument(Document document, String filePath) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }
}