package shm.dim.lab_3_4.randompersons;

import shm.dim.lab_3_4.organization.Organization;
import shm.dim.lab_3_4.units.Listener;
import shm.dim.lab_3_4.units.Student;

public class RandomPerson {

    private static String [] names = {"Anton", "Maksim", "Ivan", "Gosha", "Gleb",
                                      "Ilia", "Dima", "Pasha", "Egor", "Andrey"};

    private RandomPerson() {
    }

    private static int getRandomIntNum(int beginValue, int endValue) {
        return (beginValue + (int)(Math.random() * endValue));
    }

    public static Listener getRandomListener() {
        return new Listener(getRandomName(), getRandomAge(), getRandomOrg().toString());
    }

    public static Student getRandomStudent() {
        return new Student(getRandomName(), getRandomAge(), getRandomRating());
    }

    private static int getRandomAge() {
        return getRandomIntNum(18, 50);
    }

    private static String getRandomName() {
        return names[getRandomIntNum(0, names.length-1)];
    }

    private static long getRandomRating() {
        return (long)(getRandomIntNum(0, 100));
    }

    private static Organization getRandomOrg() {
        int size = Organization.getORGANIZATIONS().size();
        return Organization.getORGANIZATIONS().get(getRandomIntNum(0, size));
    }
}