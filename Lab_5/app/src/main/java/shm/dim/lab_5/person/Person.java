package shm.dim.lab_5.person;

public class Person {

    private String sex, activityLevel;
    private int height, weight, age;

    public Person() {
    }

    public Person(String sex, int height, int weight, int age, String activityLevel) {
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.activityLevel = activityLevel;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getActivityLevel() {
        return activityLevel;
    }
}