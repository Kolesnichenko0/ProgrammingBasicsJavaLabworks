package part1.labwork5.third;

public class Student {
    private int idNumber;
    private String name;
    private String surname;
    private double ratingScore;

    public Student(String name, String surname, double ratingScore, int idNumber) {
        this.name = name;
        this.surname = surname;
        this.ratingScore = ratingScore;
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(double ratingScore) {
        this.ratingScore = ratingScore;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getIdNumber() {
        return idNumber;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Student: name: ").append(name).append(". Surname: ")
                .append(surname).append(".\nRating score: ").append(ratingScore).append(".");
        return result.toString();
    }

    public static void main(String[] args) {
        Student student = new Student("Tom", "Gibson", 85.5, 1);

        System.out.println(student);
        System.out.println("Testing getters and setters for the student:");
        student.setName("Sam");
        student.setSurname("Jordan");
        student.setRatingScore(92.0);
        student.setIdNumber(0);

        System.out.println(student.getName());
        System.out.println(student.getSurname());
        System.out.println(student.getRatingScore());
        System.out.println(student.getIdNumber());
    }
}
