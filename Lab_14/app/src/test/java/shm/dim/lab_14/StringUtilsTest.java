package shm.dim.lab_14;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {
    @Test
    public void testFromDouble(){
        double source = 3.1415;
        String expected="3.1415";

        String actual = StringUtils.doubleToString(source);
        assertEquals("Unexpected string value", expected, actual);
        System.out.println("Test ok");
    }
}