package part1.labwork5.third;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class StoreToZip {
    public static void main(String[] args) {
        Group group = Group.createGroup();
        try (ZipOutputStream zOut = new ZipOutputStream(new FileOutputStream("resources/part1/labwork5/third/Group.zip"));
             DataOutputStream out = new DataOutputStream(zOut)) {
            for (Student student : group.getStudents()) {
                zOut.putNextEntry(new ZipEntry(Integer.toString(student.getIdNumber())));
                out.writeUTF(student.getName());
                out.writeUTF(student.getSurname());
                out.writeDouble(student.getRatingScore());
                zOut.closeEntry();
            }
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
        }
    }
}
