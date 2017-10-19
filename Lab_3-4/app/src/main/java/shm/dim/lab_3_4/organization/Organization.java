package shm.dim.lab_3_4.organization;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Organization {
    BSU(900), BNTU(630), BSTU(850);

    private int price;

    Organization(int price){
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public static List<Organization> getORGANIZATIONS(){
        return Collections.unmodifiableList(Arrays.asList(values()));
    }
}