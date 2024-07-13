package part2.labwork3.sixth;

public class StudentGroupSAXProgram {
    private static final String XML_FILE_NAME = "storage/part2/labwork3/sixth/group.xml";

    public static void main(String[] args) {
        System.out.println("\033[1;32m" + "Starting processing XML file with SAX..." + "\033[0m");
        new StudentGroupSAXProcessor().process(XML_FILE_NAME);
        System.out.println("\033[1;32m" + "Finished processing XML file with SAX." + "\033[0m");
    }
}