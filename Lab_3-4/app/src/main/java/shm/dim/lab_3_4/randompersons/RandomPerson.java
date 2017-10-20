package shm.dim.lab_3_4.randompersons;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import shm.dim.lab_3_4.organization.Organization;
import shm.dim.lab_3_4.units.Listener;
import shm.dim.lab_3_4.units.Student;

public class RandomPerson {

    private static String [] names = {"Anton", "Maksim", "Ivan", "Gosha", "Gleb",
                                      "Ilia", "Dima", "Pasha", "Egor", "Andrey"};

    private RandomPerson() {
    }

    private static <T extends Number> int getRandomNum(T beginValue, T endValue) {
        return (beginValue.intValue() + (int)(Math.random() * endValue.intValue()));
    }

    public static Listener getRandomListener() {
        return new Listener(getRandomName(), getRandomAge(), getRandomOrg().toString());
    }

    public static Student getRandomStudent() {
        return new Student(getRandomName(), getRandomAge(), getRandomRating());
    }

    private static int getRandomAge() {
        return getRandomNum(18, 50);
    }

    private static String getRandomName() {
        return names[getRandomNum(0, names.length-1)];
    }

    private static double getRandomRating() {
        return (double)(getRandomNum(0.0, 100.0));
    }

    private static Organization getRandomOrg() {
        Organization.IOrganizationList orgList = () ->
                Collections.unmodifiableList(Arrays.asList(Organization.values()));

        int size = orgList.getOrganizationList().size();
        List<Organization> organizationList = orgList.getOrganizationList();

        return organizationList.get(getRandomNum(0, size));
    }
}