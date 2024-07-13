package part2.labwork3.sixth;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class StudentGroupSAXProcessor {
    private static class Handler extends DefaultHandler {
        private String currentElement;
        private String studentName;
        private String studentSurname;
        private String ratingScore;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentElement = qName;
            switch (qName) {
                case "groupData" -> {
                    String groupName = attributes.getValue("name");
                    System.out.println("Group name: " + groupName + ".");
                }
                case "StudentsData" -> System.out.println("Students:");
                case "StudentData" -> {
                    studentName = attributes.getValue("name");
                    studentSurname = attributes.getValue("surname");
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("StudentData")) {
                System.out.println("Student: name: " + studentName + ". Surname: " + studentSurname + ".");
                System.out.println("Rating score: " + ratingScore + ".");
            }
            currentElement = "";
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (currentElement.equals("RatingScoreData")) {
                ratingScore = new String(ch, start, length).trim();
            }
        }
    }

    public void process(String xmlFilePath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(xmlFilePath, new Handler());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
