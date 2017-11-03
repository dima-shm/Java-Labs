package shm.dim.lab_6_7.activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import shm.dim.lab_6_7.R;
import shm.dim.lab_6_7.file_helper.FileHelper;
import shm.dim.lab_6_7.student.Student;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ResultActivity extends AppCompatActivity {

    private TextView tvSurname, tvName, tvMiddleName, tvBirthday;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initViews();

        student = getIntent().getParcelableExtra("Student");

        setTextOnTextView();
    }

    private void initViews() {
        tvSurname = (TextView)findViewById(R.id.tvSurname);
        tvName = (TextView)findViewById(R.id.tvName);
        tvMiddleName = (TextView)findViewById(R.id.tvMiddleName);
        tvBirthday = (TextView)findViewById(R.id.tvBirthday);
    }

    private void setTextOnTextView() {
        tvSurname.setText(student.getSurname());
        tvName.setText(student.getName());
        tvMiddleName.setText(student.getMiddleName());
        tvBirthday.setText(student.getBirthday());
    }

    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClick_Save(View view) {
        FileHelper.write(student);

        goToMain();
    }
    public void onClick_Back(View view){
        Intent intent = new Intent(this, BirthdayActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}