package shm.dim.lab_3_4.educationmanager;

import shm.dim.lab_3_4.exception.EduException;
import shm.dim.lab_3_4.staff.Staff;

public interface IAction {
    Staff createGroup(String filename);
    Staff generateCourse(int maxStudentsCount, int maxListenersCount) throws EduException;
    int sumRanges(Staff anyCourse);
    int countListener(Staff anyCourse);
    Staff sortbyYear(Staff anyCourse);
}