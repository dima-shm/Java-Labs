package shm.dim.lab_6_7.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import shm.dim.lab_6_7.R;
import shm.dim.lab_6_7.dialog_message.DialogMessage;
import shm.dim.lab_6_7.student.Student;

public class NameActivity extends AppCompatActivity {

    private EditText etName, etSurname, etMiddleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        initViews();
    }

    private void initViews() {
        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        etMiddleName = (EditText) findViewById(R.id.etMiddleName);
    }

    private boolean isEmptyFields() {
        return (etName.getText().toString().equals("") ||
                etSurname.getText().toString().equals("") ||
                etMiddleName.getText().toString().equals(""));
    }

    public void onClick_Next(View view) {
        if (!isEmptyFields()) {
            Intent intent = new Intent(this, BirthdayActivity.class);
            intent.putExtra("Student", new Student(etName.getText().toString(),
                    etSurname.getText().toString(),
                    etMiddleName.getText().toString(),
                    ""));
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        } else {
            DialogMessage.create(NameActivity.this, "Проверьте, заполнены ли все поля");
            DialogMessage.show();
        }
    }
    public void onClick_Back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}