package shm.dim.lab_8_9;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

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
        startActivity(new Intent(AdministrationActivity.this, AdministrationAddUserActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    private void deleteUser() {

    }

    private void changeUser() {

    }
}