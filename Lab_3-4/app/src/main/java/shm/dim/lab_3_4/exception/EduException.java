package shm.dim.lab_3_4.exception;

public class EduException extends Exception {

    private int number;

    public EduException(String message, int num) {
        super(message);
        number = num;
    }

    public int getNumber() {
        return number;
    }
}