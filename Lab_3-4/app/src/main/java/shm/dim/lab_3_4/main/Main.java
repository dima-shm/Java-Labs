package shm.dim.lab_3_4.main;

import android.os.Build;
import android.support.annotation.RequiresApi;

import shm.dim.lab_3_4.educationmanager.Manager;
import shm.dim.lab_3_4.exception.EduException;
import shm.dim.lab_3_4.staff.Staff;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Main {

    public static void main(String[] args) {
        try {
            Manager mng = new Manager();
            //mng.generateCourse(-4, 2); // test EduException
            Staff staff = mng.generateCourse(4, 2);

            System.out.println(staff.toString());
            System.out.println("Sum money:    " + mng.sumRanges(staff));
            System.out.println("Count person: " + mng.countListener(staff));

            staff = mng.sortbyYear(staff);
            System.out.println("\nAfter sorting by year:");
            System.out.println(staff.toString());

            staff = mng.createGroup("students.json");
            System.out.println("Stuff from JSON: ");
            System.out.println(staff.toString());

            staff = mng.sortbyName(staff);
            System.out.println("After sorting by name:");
            System.out.println(staff.toString());
        }
        catch (EduException exp) {
            System.out.println("EduException: (" + exp.getNumber() + ") " + exp.getMessage());
        }
    }
}