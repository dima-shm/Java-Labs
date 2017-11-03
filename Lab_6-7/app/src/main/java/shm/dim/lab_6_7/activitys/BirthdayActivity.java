package shm.dim.lab_6_7.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import shm.dim.lab_6_7.R;
import shm.dim.lab_6_7.dialog_message.DialogMessage;
import shm.dim.lab_6_7.student.Student;

public class BirthdayActivity extends AppCompatActivity {

    private EditText etBirthday;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        initViews();

        student = getIntent().getParcelableExtra("Student");
    }

    private void initViews() {
        etBirthday = (EditText) findViewById(R.id.etBirthday);
    }

    private boolean checkFormatDate(String str) {
        String DATE_FORMAT = "dd.MM.yyyy";
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        df.setLenient(false);
        return df.parse(str, new ParsePosition(0)) != null;
    }

    public void onClick_Next(View view) {
        if (checkFormatDate(etBirthday.getText().toString())) {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("Student", new Student(student.getName(),
                    student.getSurname(),
                    student.getMiddleName(),
                    etBirthday.getText().toString()));
            startActivity(intent);
        } else {
            DialogMessage.create(BirthdayActivity.this, "Введенная дата не удовлетворяет формату");
            DialogMessage.show();
        }
    }
    public void onClick_Back(View view){
        Intent intent = new Intent(this, NameActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}