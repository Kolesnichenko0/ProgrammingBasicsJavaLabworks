package part1.labwork5.third;

public class Group {
    private String name;
    private Student[] students;

    public Group(String name, Student... students) {
        this.name = name;
        this.students = students;
    }

    public Group(String name) {
        this(name, new Student[0]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student... students) {
        this.students = students;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Group name: ").append(name).append(".\n");
        if (students.length == 0) {
            result.append("There are no students.\n");
        } else {
            result.append("Students:\n");
            for (Student student : getStudents()) {
                result.append(student).append("\n");
            }
        }
        return result.toString();
    }

    public static Group createGroup() {
        Student student1 = new Student("Tom", "Gibson", 85.5, 0);
        Student student2 = new Student("Alice", "Smith", 92.0, 1);
        Student student3 = new Student("Bob", "Jordan", 78.0, 2);
        return new Group("A21", student1, student2, student3);
    }

    public static void main(String[] args) {
        System.out.println("Testing a constructor with a name and students:");
        Group group1 = createGroup();
        System.out.println(group1);
        group1.setName("A22");
        System.out.println(group1);
        System.out.println("Testing a constructor with a name:");
        Group group2 = new Group("A33");

        System.out.println("Group name: " + group2.getName() + ".");

        Student student4 = new Student("John", "Smith", 85.2, 0);
        Student student5 = new Student("Kate", "Smith", 92.3, 1);
        group2.setStudents(student4, student5);
        System.out.println(group2);
    }
}
