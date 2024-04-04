package part1.labwork5.third;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReadFromZip {
    public static void main(String[] args) {
        System.out.println("Students:");
        try (ZipInputStream zIn = new ZipInputStream(new FileInputStream("resources/part1/labwork5/third/Group.zip"));
             DataInputStream in = new DataInputStream(zIn)) {
            ZipEntry entry;
            while ((entry = zIn.getNextEntry()) != null) {
                System.out.println("Id number: " + entry.getName());
                System.out.println("Name: " + in.readUTF());
                System.out.println("Surname: " + in.readUTF());
                System.out.println("Rating score: " + in.readDouble());
                System.out.println();
                zIn.closeEntry();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Read failed or no path found");
            e.printStackTrace();
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
        }
    }
}
