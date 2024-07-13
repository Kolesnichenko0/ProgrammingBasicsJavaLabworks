package part2.labwork3.fourth;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import part1.labwork5.third.Group;
import part1.labwork5.third.Student;

public class StudentGroupXStreamJSONSerializationProcessor {
    private XStream xStream;

    public StudentGroupXStreamJSONSerializationProcessor() {
        this.xStream = new XStream(new JsonHierarchicalStreamDriver());
        this.xStream.alias("student", Student.class);
        this.xStream.alias("group", Group.class);
    }

    public String serializeToJSON(Object obj) {
        return xStream.toXML(obj);
    }
}