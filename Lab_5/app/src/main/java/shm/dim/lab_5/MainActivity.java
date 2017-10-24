package shm.dim.lab_5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import shm.dim.lab_5.calc_calories.CalculatorCalories;
import shm.dim.lab_5.person.Person;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private EditText etWeight,
                     etHeight,
                     etAge;
    private Spinner spinnerActivityLevel;
    private TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        etWeight = (EditText)findViewById(R.id.etWeight);
        etHeight = (EditText)findViewById(R.id.etHeight);
        etAge = (EditText)findViewById(R.id.etAge);
        spinnerActivityLevel = (Spinner)findViewById(R.id.spinner);
        tvResults = (TextView)findViewById(R.id.tvResults);
    }

    private void createDialogMsg(String msg) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setMessage(msg).setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                    }
                });
        AlertDialog ad = b.create();
        ad.show();
    }

    private boolean checkField () {
        RadioButton rb = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        return (rb != null &&
                !etWeight.getText().toString().isEmpty() &&
                !etHeight.getText().toString().isEmpty() &&
                !etAge.getText().toString().isEmpty());
    }

    public String getSex() {
        RadioButton rb = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        return rb.getText().toString();
    }

    public void onClickCalculateResults(View v) {
        if(checkField()) {
            Person person = new Person();
            person.setSex(getSex());
            person.setWeight(Integer.valueOf(etWeight.getText().toString()));
            person.setHeight(Integer.valueOf(etHeight.getText().toString()));
            person.setAge(Integer.valueOf(etAge.getText().toString()));
            person.setActivityLevel(spinnerActivityLevel.getSelectedItem().toString());
            tvResults.setText(String.valueOf(CalculatorCalories.calcCalories(person)));
        } else
            createDialogMsg("Проверьте, заполнены ли все поля");
    }
}