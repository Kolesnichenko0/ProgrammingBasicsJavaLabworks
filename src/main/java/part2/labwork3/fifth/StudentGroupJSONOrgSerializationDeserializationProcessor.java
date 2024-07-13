package part2.labwork3.fifth;

import org.json.JSONArray;
import org.json.JSONObject;
import part1.labwork5.third.Group;
import part1.labwork5.third.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentGroupJSONOrgSerializationDeserializationProcessor {

    public String serializeToJSON(Group group) {
        JSONObject mainJson = new JSONObject();
        JSONObject groupJson = new JSONObject();
        groupJson.put("name", group.getName());

        JSONArray studentsJson = new JSONArray();
        for (Student student : group.getStudents()) {
            JSONObject studentJson = new JSONObject();
            studentJson.put("idNumber", student.getIdNumber());
            studentJson.put("name", student.getName());
            studentJson.put("surname", student.getSurname());
            studentJson.put("ratingScore", student.getRatingScore());
            studentsJson.put(studentJson);
        }

        groupJson.put("students", studentsJson);
        mainJson.put("group", groupJson);
        return mainJson.toString(1);
    }

    public Group deserializeFromJSON(String json) {
        JSONObject mainJson = new JSONObject(json);
        JSONObject groupJson = mainJson.getJSONObject("group");

        String groupName = groupJson.getString("name");

        List<Student> students = new ArrayList<>();
        JSONArray studentsJson = groupJson.getJSONArray("students");
        for (int i = 0; i < studentsJson.length(); i++) {
            JSONObject studentJson = studentsJson.getJSONObject(i);
            int idNumber = studentJson.getInt("idNumber");
            String name = studentJson.getString("name");
            String surname = studentJson.getString("surname");
            double ratingScore = studentJson.getDouble("ratingScore");
            students.add(new Student(name, surname, ratingScore, idNumber));
        }

        return new Group(groupName, students.toArray(new Student[0]));
    }
}
