package part2.labwork3.fourth;

import part1.labwork5.third.Group;

import java.io.IOException;

public class GroupXStreamSerializationProgram {
    private static final String XML_FILE_NAME = "storage/part2/labwork3/fourth/group.xml";
    private static final String JSON_FILE_NAME = "storage/part2/labwork3/fourth/group.json";

    public static void main(String[] args) {
        Group group = Group.createGroup();

        StudentGroupXStreamXMLSerializationDeserializationProcessor xmlProcessor =
                new StudentGroupXStreamXMLSerializationDeserializationProcessor();
        StudentGroupXStreamJSONSerializationProcessor jsonProcessorWrite =
                new StudentGroupXStreamJSONSerializationProcessor();
        StudentGroupXStreamJSONDeserializationProcessor jsonProcessorRead =
                new StudentGroupXStreamJSONDeserializationProcessor();
        try {
            System.out.println("\033[1;32m" + "Initial Group object:" + "\033[0m");
            System.out.println(group);

            String xml = xmlProcessor.serializeToXML(group);
            String json = jsonProcessorWrite.serializeToJSON(group);

            FileProcessor.writeToFile(XML_FILE_NAME, xml);
            FileProcessor.writeToFile(JSON_FILE_NAME, json);

            System.out.println("\033[1;32m" + "XML representation:" + "\033[0m");
            System.out.println(xml);

            System.out.println("\n\033[1;32m" + "JSON representation:" + "\033[0m");
            System.out.println(json);

            Group deserializedGroupFromXML = (Group) xmlProcessor.deserializeFromXML(FileProcessor
                    .readFromFile(XML_FILE_NAME));
            Group deserializedGroupFromJSON = (Group) jsonProcessorRead.deserializeFromJSON(FileProcessor
                    .readFromFile(JSON_FILE_NAME));

            System.out.println("\n\033[1;32m" + "Deserialized Group object from XML:" + "\033[0m");
            System.out.println(deserializedGroupFromXML);

            System.out.println("\033[1;32m" + "Deserialized Group object from JSON:" + "\033[0m");
            System.out.println(deserializedGroupFromJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}