package part2.labwork3.fifth;

import part1.labwork5.third.Group;
import part2.labwork3.fourth.FileProcessor;

import java.io.IOException;

public class GroupJSONOrgSerializationProgram {
    private static final String JSON_FILE_NAME = "storage/part2/labwork3/fifth/group.json";

    public static void main(String[] args) {
        Group group = Group.createGroup();

        StudentGroupJSONOrgSerializationDeserializationProcessor jsonProcessor =
                new StudentGroupJSONOrgSerializationDeserializationProcessor();
        try {
            System.out.println("\033[1;32m" + "Initial Group object:" + "\033[0m");
            System.out.println(group);

            String json = jsonProcessor.serializeToJSON(group);

            FileProcessor.writeToFile(JSON_FILE_NAME, json);

            System.out.println("\033[1;32m" + "JSON representation:" + "\033[0m");
            System.out.println(json);

            Group deserializedGroupFromJSON = (Group) jsonProcessor.deserializeFromJSON(FileProcessor
                    .readFromFile(JSON_FILE_NAME));


            System.out.println("\n\033[1;32m" + "Deserialized Group object from JSON:" + "\033[0m");
            System.out.println(deserializedGroupFromJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}