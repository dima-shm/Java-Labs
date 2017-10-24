package shm.dim.lab_2.basejava;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Arrays;

import static java.lang.Math.log;

/**
 * @author Dima Shumanski
 * @version 1.0
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class JavaTestTepy {

    private static byte   b = 127;
    private static short sh = 32_413;
    private static int    i = 2_123_123_123;
    private static long   l = 11_261_513_452_342L;

    private static float  f = 15.85F;
    private static double d = 32.13;

    private static char  ch = 'K';
    private static String s = "string";

    private static boolean bool = true;

    static int sint;


    final int FIRSTVAL = 0;
    public final int SECONDVAL = 0;
    /**
     * @value static final int
     * @see JavaTestTepy
     */
    public static final int THRIDVAL = 0;


    /**
     * @return void
     * @throws Exception
     * @param args
     */
    public static void main(String[] args) {
        taskB();
        taskD();
        taskE();
        taskF();
        taskG();
        taskH();
    }

    static void taskB() {
        System.out.println("String(" + s + ") + Int(" + i + ") = " + (s + i));
        System.out.println("String(" + s + ") + Char(" + ch + ") = " + (s + ch));
        System.out.println("String(" + s + ") + Double(" + d + ") = " + (s + d));
        System.out.println("Byte(" + b + ") + Int(" + i + ") = Byte(" + (byte) (b + i) + ")");
        System.out.println("Double(" + d + ") + Long(" + l + ") = Int(" + (int) (d + l) + ")");
        System.out.println("Int(" + i + ") + " + 2147483647 + "= Long(" + (i + 2147483647L) + ")");
        System.out.println("static int sint = " + sint);
        System.out.println("Boolean(" + bool + ") && Boolean(" + bool + ") = " + "Boolean(" + (bool && bool) + ")");
        System.out.println("Boolean(" + bool + ") ^ Boolean(" + false + ") = " + "Boolean(" + (bool ^ false) + ")");
        //System.out.println("Boolean(" + bool + ") + Boolean(" + false + ") = " + "Boolean(" + (bool + false) + ")");
        long longValue = 9_223_372_036_854_775_807L;
        long hexValue = 0x7fff_ffff_fffL;
        System.out.println("Char(" + ch + "/" + (int) ch + ") - 'a'(" + (int) 'a' + ") = " + (ch - 'a'));
        System.out.println("Char(" + ch + "/" + (int) ch + ") - '\u0061'(" + (int) '\u0061' + ") = " + (ch - '\u0061'));
        System.out.println("Char(" + ch + "/" + (int) ch + ") - 97 = " + (ch - 97));
        System.out.println("3.45 % 2.4 = " + (3.45 % 2.4));
        System.out.println("1.0/0.0 = " + (1.0 / 0.0));
        System.out.println("0.0/0.0 = " + (0.0 / 0.0));
        System.out.println("log(-345); = " + (log(-345)));
        System.out.println("Float.intBitsToFloat(0x7F800000) = " + Float.intBitsToFloat(0x7F800000));
        System.out.println("Float.intBitsToFloat(0xFF800000) = " + Float.intBitsToFloat(0xFF800000));
    }

    static void taskD() {
        System.out.println("Math.PI = " + Math.PI);
        System.out.println("Math.E = " + Math.E);
        System.out.println("Math.round(Math.E) = " + Math.round(Math.E));
        System.out.println("Math.min(Math.E, Math.PI) = " + Math.min(Math.E, Math.PI));
        System.out.println("Math.random() = " + Math.random());
    }

    static void taskE() {
        Boolean bool = new Boolean(true);
        Character ch = new Character('F');
        Integer i = new Integer(1_124);
        Byte b = new Byte(String.valueOf(34));
        Short sh = new Short(String.valueOf(123));
        Long l = new Long(13_124_312L);
        Double d = new Double(4.12);

        System.out.println("Long(" + l + ") + Double(" + d + ") = " + (d+l));
        System.out.println("Byte(" + b + ") - Short(" + sh + ") = " + (b-sh));
        System.out.println("Character(" + ch + ") * Integer(" + i + ") = " + (ch*i));
        System.out.println("Long(" + l + ") & Integer(" + i + ") = " + (l&i));
        System.out.println("~Integer(" + i + ") = " + ~i);
        System.out.println("Integer(" + i + ") >> 1 = " + (i >> 1));
        System.out.println("Integer(" + i + ") >>> 1 = " + (i >>> 1));

        System.out.println("Long MAX_VALUE = " + Long.MAX_VALUE);
        System.out.println("Long MIN_VALUE = " + Long.MIN_VALUE);
        System.out.println("Double MAX_VALUE = " + Double.MAX_VALUE);
        System.out.println("Double MIN_VALUE = " + Double.MIN_VALUE);

        Integer intValue = new Integer(143);
        Byte byteValue = new Byte(String.valueOf(23));
        int a = intValue.intValue();
        byte g = byteValue.byteValue();

        System.out.println("Integer.parseInt(\"11\") = " + Integer.parseInt("11"));
        System.out.println("Integer.toHexString(12) = " + Integer.toHexString(12));
        System.out.println("Integer.compare(13, 14) = " + Integer.compare(13, 14));
        System.out.println("Integer.toString(15) = " + Integer.toString(15));
        System.out.println("Integer.bitCount(16) = " + Integer.bitCount(16));
    }

    static void taskF() {
        String s34 = "2345";
        System.out.println("Integer.valueOf(s) = " + Integer.valueOf(s34));
        byte b[] = s34.getBytes();
        System.out.println(b);
        String s = new String(b);
        System.out.println(s);

        Boolean bool1 = Boolean.valueOf(s);
        Boolean bool2 = Boolean.parseBoolean(s);
        System.out.println("bool1 = " + bool1);
        System.out.println("bool2 = " + bool2);

        String s1 , s2;
        s1 = s2 = "hello";
        System.out.println("(s1==s2) = " + (s1==s2));
        System.out.println("s1.equals(s2) = " + (s1.equals(s2)));
        System.out.println("s1.compareTo(s2) = " + (s1.compareTo(s2)));
        s2 = null;
        System.out.println("(s1==s2) = " + (s1==s2));
        System.out.println("s1.equals(s2) = " + (s1.equals(s2)));
        //System.out.println("s1.compareTo(s2) = " + (s1.compareTo(s2)));

        System.out.println("s1.contains(\"l\") = " + s1.contains("l"));
        System.out.println("s1.hashCode() = " + s1.hashCode());
        System.out.println("s1.indexOf(\"e\") = " + s1.indexOf("e"));
        System.out.println("s1.length() = " + s1.length());
        System.out.println("s1.replace(\"e\", \"i\") = " + s1.replace("e", "i"));
        String words[] = s1.split("e");
        System.out.println("s1.split(\"l\") = " + Arrays.toString(words));
    }

    static void taskG() {
        char[][] c1;
        char[] c2[];
        char c3[][];

        c1 = new char[3][];
        for(int i = 0; i < 3; i++)
            c1[i] = new char[i+1];
        System.out.println("c1.length = " + c1.length);
        System.out.println("c1[0].length = " + c1[0].length);
        System.out.println("c1[1].length = " + c1[1].length);
        System.out.println("c1[2].length = " + c1[2].length);

        c2 = new char[][]{
                {'a', 'b', 'f'},
                {'u', 'g'}
        };
        c3 = new char[][]{
                {'t', 'g'},
                {'l', 'j'}
        };
        boolean comRez = c2==c3;
        System.out.println("comRez = " + comRez);
        c2 = c3;
        for (char[] i : c2)
            System.out.println(i);
        //System.out.println("c2[2][45] = " + c2[2][45]);
    }

    static void taskH() {
        WrapperString wstr = new WrapperString("hello");
        System.out.println("wStr = " + wstr.getStr());
        wstr.replace('e', 'i');
        System.out.println("wStr = " + wstr.getStr());

        WrapperString wStr = new WrapperString("hello") {
            public void replace(char oldchar, char newchar) {
                setStr(getStr().replace(oldchar, newchar));
            }

            public void delete(char newchar) {
                setStr(getStr().replace(newchar, '\0'));
            }
        };
        System.out.println("wStr = " + wStr.getStr());
        wStr.replace('e', 'i');
        System.out.println("wStr = " + wStr.getStr());
        wStr.delete('i');
        System.out.println("wStr = " + wStr.getStr());
    }
}