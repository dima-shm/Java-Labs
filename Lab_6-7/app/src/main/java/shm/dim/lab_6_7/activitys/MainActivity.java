package shm.dim.lab_6_7.activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;

import shm.dim.lab_6_7.R;
import shm.dim.lab_6_7.file_helper.FileHelper;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    private TextView tvJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkFile();

        initViews();

        tvJson.setText(FileHelper.read());
    }

    private void checkFile() {
        new FileHelper(new File(getFilesDir(), "Json.json"));
        if(!FileHelper.exist())
            FileHelper.create();
    }

    private void initViews() {
        tvJson = (TextView) findViewById(R.id.tvJson);
    }

    public void onClick_AddPerson(View view) {
        Intent intent = new Intent(this, NameActivity.class);
        startActivity(intent);
    }
}