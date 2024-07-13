package part2.labwork3.fourth;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import part1.labwork5.third.Group;
import part1.labwork5.third.Student;

public class StudentGroupXStreamXMLSerializationDeserializationProcessor {
    private XStream xStream;

    public StudentGroupXStreamXMLSerializationDeserializationProcessor() {
        this.xStream = new XStream();
        this.xStream.alias("student", Student.class);
        this.xStream.alias("group", Group.class);
        this.xStream.addPermission(NoTypePermission.NONE);
        this.xStream.allowTypes(new Class[]{Student.class, Group.class});
    }

    public String serializeToXML(Object obj) {
        return xStream.toXML(obj);
    }

    public Object deserializeFromXML(String xml) {
        return xStream.fromXML(xml);
    }
}