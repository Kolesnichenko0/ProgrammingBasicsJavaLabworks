package part2.labwork3.sixth;

public class StudentGroupDomProgram {
    private static final String INPUT_XML_FILE_NAME = "storage/part2/labwork3/sixth/group.xml";
    private static final String OUTPUT_XML_FILE_NAME = "storage/part2/labwork3/sixth/group_modified.xml";
    private static final double INCREASE_SCORE_BY = 1.0;

    public static void main(String[] args) {
        System.out.println("\033[1;32m" + "Starting processing XML file with DOM..." + "\033[0m");
        new StudentGroupDomProcessor().process(INPUT_XML_FILE_NAME, OUTPUT_XML_FILE_NAME, INCREASE_SCORE_BY);
        System.out.println("\033[1;32m" + "Finished processing XML file with DOM." + "\033[0m");
    }
}