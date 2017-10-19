package shm.dim.lab_3_4.units;

public class Student extends Person {

    private double rating;

    public Student(String name, int age, double rating) {
        super(name, age);
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(this.name + " " + this.age + " " + getRating());
        return string.toString();
    }
}