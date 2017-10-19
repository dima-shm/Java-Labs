package shm.dim.lab_3_4.units;

import shm.dim.lab_3_4.organization.Organization;

public class Listener extends Person {

    private Organization organization;

    public Listener(String name, int age, String organization) {
        super(name, age);
        this.organization = Organization.valueOf(organization);
    }

    public int getPrice(){
        return organization.getPrice();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(this.name + " " + this.age + " " + organization.toString());
        return string.toString();
    }
}