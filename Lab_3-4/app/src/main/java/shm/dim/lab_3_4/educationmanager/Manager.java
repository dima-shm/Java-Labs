package shm.dim.lab_3_4.educationmanager;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Comparator;

import shm.dim.lab_3_4.exception.EduException;
import shm.dim.lab_3_4.randompersons.RandomPerson;
import shm.dim.lab_3_4.staff.Staff;
import shm.dim.lab_3_4.units.Listener;
import shm.dim.lab_3_4.units.Person;
import shm.dim.lab_3_4.units.Student;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Manager implements IAction {

    private void getJsonListeners(Staff staff, JSONObject jsonObject) {
        JSONArray listeners = (JSONArray) jsonObject.get("listeners");
        for (Object listener1 : listeners) {
            JSONObject listener = (JSONObject) listener1;
            String name = (String) listener.get("name");
            long age = (Long) listener.get("age");
            String organization = (String) listener.get("organization");
            staff.add(new Listener(name, (int) age, organization));
        }
    }

    private void getJsonStudents(Staff staff, JSONObject jsonObject) {
        JSONArray students = (JSONArray) jsonObject.get("students");
        for (Object student1 : students) {
            JSONObject student = (JSONObject) student1;
            String name = (String) student.get("name");
            long age = (Long) student.get("age");
            double rating = (Double) student.get("rating");
            staff.add(new Student(name, (int) age, rating));
        }
    }

    @Override
    public Staff createGroup(String filename) {
        Staff staff = new Staff();
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(filename);
            Object jsonObj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) jsonObj;
            getJsonStudents(staff, jsonObject);
            getJsonListeners(staff, jsonObject);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return staff;
    }

    @Override
    public Staff generateCourse(int maxStudentsCount, int maxListenersCount) throws EduException {
        if(maxStudentsCount < 0 || maxListenersCount < 0) {
            throw new EduException("Student number or listeners number cannot be less zero", 911);
        }
        Staff staff = new Staff();
        for(int i = 0; i < maxStudentsCount; i++) {
            staff.add(RandomPerson.getRandomStudent());
        }
        for(int i = 0; i < maxListenersCount; i++) {
            staff.add(RandomPerson.getRandomListener());
        }
        return staff;
    }

    @Override
    public int sumRanges(Staff anyCourse) {
        int sum = 0;
        for(Person person: anyCourse.getStudentList()) {
            if(person.getClass().getName().equals("shm.dim.lab_3_4.units.Listener"))
                sum += ((Listener) person).getPrice();
        }
        return sum;
    }

    @Override
    public int countListener(Staff anyCourse) {
        return anyCourse.getStudentList().size();
    }

    @Override
    public Staff sortbyYear(Staff anyCourse) {
        anyCourse.getStudentList().sort(Comparator.comparingInt(Person::getAge));
        return anyCourse;
    }

    public Staff sortbyName(Staff anyCourse){
        anyCourse.getStudentList().sort(Comparator.comparing(Person::getName));
        return anyCourse;
    }
}