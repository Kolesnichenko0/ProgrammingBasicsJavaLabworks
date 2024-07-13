package part2.labwork3.fourth;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import part1.labwork5.third.Group;
import part1.labwork5.third.Student;

public class StudentGroupXStreamJSONDeserializationProcessor {
    private XStream xStream;

    public StudentGroupXStreamJSONDeserializationProcessor() {
        this.xStream = new XStream(new JettisonMappedXmlDriver());
        this.xStream.alias("student", Student.class);
        this.xStream.alias("group", Group.class);
        this.xStream.addPermission(NoTypePermission.NONE);
        this.xStream.allowTypes(new Class[]{Student.class, Group.class});
        this.xStream.addImplicitArray(Group.class, "students", Student.class);
    }

    public Object deserializeFromJSON(String json) {
        return xStream.fromXML(json);
    }
}