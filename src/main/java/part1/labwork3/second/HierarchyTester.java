package part1.labwork3.second;

/**
 * The {@code Human} class represents a basic human being with a {@code name}, {@code height}, and {@code weight}.
 */
class Human {
    private String name;
    private double height;
    private double weight;

    public Human(String name, double height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ".\nHeight: " + getHeight() + " cm.\nWeight: " + getWeight() + " kg.\n";
    }
}

/**
 * The {@code Citizen} class represents a citizen, which is a {@code human} with a nationality.
 */
class Citizen extends Human {
    private String nationality;

    public Citizen(String name, double height, double weight, String nationality) {
        super(name, weight, height);
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return super.toString() + "Nationality: " + getNationality() + ".\n";
    }
}

/**
 * The {@code Student} class represents a Student, which is a {@code citizen} with a university.
 */
class Student extends Citizen {
    private String university;

    public Student(String name, double height, double weight,
                   String nationality, String university) {
        super(name, weight, height, nationality);
        this.university = university;
    }

    public String getUniversity() {
        return university;
    }

    @Override
    public String toString() {
        return super.toString() + "University: " + getUniversity() + ".\n";
    }
}

/**
 * The {@code Employee} class represents an {@code Employee}, which is a {@code citizen} with a job in {@code companyName}.
 */
class Employee extends Citizen {
    private String companyName;

    public Employee(String name, double height, double weight,
                    String nationality, String companyName) {
        super(name, weight, height, nationality);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return super.toString() + "Company name: " + getCompanyName() + ".\n";
    }
}

/**
 * The {@code HierarchyTester} class is used to test the hierarchy of {@code human} objects.
 */
public class HierarchyTester {
    public static void main(String[] args) {
        Human[] humans = {new Human("Tom", 178, 66.2),
                new Citizen("Thomas", 162.5, 50.2, "Italian"),
                new Student("Pavlo", 190.7, 70.8,
                        "Ukrainian", "NTU \"Kharkiv Polytechnic Institute\""),
                new Employee("Dmitriy", 189.4, 68.2,
                        "Ukrainian", "GlobalLogic")};
        System.out.println("Information about Humans:");
        for (Human human : humans) {
            System.out.println(human);
        }
    }
}
