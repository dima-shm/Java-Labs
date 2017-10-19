package shm.dim.lab_3_4.staff;

import java.util.ArrayList;
import java.util.List;
import shm.dim.lab_3_4.units.Person;

public class Staff {

    private ArrayList<Person> studentList;

    public Staff() {
        studentList = new ArrayList<Person>();
    }

    public Staff(ArrayList<Person> persons) {
        studentList = persons;
    }

    public void setStudentList(ArrayList<Person> studList){
        this.studentList = studList;
    }

    public List<Person> getStudentList() {
        return studentList;
    }

    public void add (Person item) {
        studentList.add(item);
    }

    public void remove (Person item) {
        studentList.remove(item);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Person p : studentList) {
            result.append(p.toString()).append("\n");
        }
        return result.toString();
    }
}