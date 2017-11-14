package shm.dim.lab_6_7.file_helper;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import shm.dim.lab_6_7.student.Student;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class FileHelper {

    private static File file;

    public FileHelper(File f) {
        file = f;
    }

    // Проверка наличия файла
    public static boolean exist() {
        boolean rc;
        if(rc = file.exists())
            Log.d("Log_06_07", "Файл " + file.getName() + " существует");
        else
            Log.d("Log_06_07", "Файл " + file.getName() + " не найден");
        return rc;
    }

    // Создание файла
    public static void create() {
        try {
            file.createNewFile();
            Log.d("Log_06_07", "Файл " + file.getName() + " создан");
        }
        catch (IOException e) {
            Log.d("Log_06_07", e.getMessage());
        }
    }

    // Запись в файл
    public static void write(Student student) {
        try (FileOutputStream fos = new  FileOutputStream(file, true)) {
            String json = new Gson().toJson(student) + "\n";
            fos.write(json.getBytes());
        }
        catch (IOException e) {
            Log.d("Log_06_07", e.getMessage());
        }
    }

    // Прочитать файл
    public static String read() {
        String str = new String();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null)
                str += (new Gson().fromJson(line, Student.class));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (str != null)
            return str.toString();
        else
            return "";
    }
}