package shm.dim.lab_8_9;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AdministrationActivity extends AppCompatActivity {

    private Toolbar mToolbar;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add_user:
                addUser();
                return true;
            case R.id.delete_user:
                deleteUser();
                return true;
            case R.id.change_info_about_user:
                changeUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        mToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
    }


    private void addUser() {
        Toast.makeText(getApplicationContext(), "addUser", Toast.LENGTH_SHORT).show();
    }

    private void deleteUser() {
        Toast.makeText(getApplicationContext(), "deleteUser", Toast.LENGTH_SHORT).show();
    }

    private void changeUser() {
        Toast.makeText(getApplicationContext(), "changeUser", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setTitle("Выход")
                .setMessage("Вы уверены, что хотите выйти из аккаунта?")
                .setNegativeButton("Отмена", null)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(AdministrationActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).create().show();
    }
}