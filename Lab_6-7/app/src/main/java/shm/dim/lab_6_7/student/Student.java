package shm.dim.lab_6_7.student;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    private String name, surname, middleName, birthday;

    public Student(Parcel source) {
        String[] data = new String[4];
        source.readStringArray(data);
        name= data[0];
        surname = data[1];
        middleName = data[2];
        birthday = data[3];
    }

    public Student(String name, String surname, String middleName, String birthday){
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.birthday = birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return name       + " " +
               surname    + " " +
               middleName + " " +
               birthday + "\n";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] { name, surname, middleName, birthday });
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}