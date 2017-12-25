package shm.dim.lab_16;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> titleList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.list_view);

        titleList.clear();
        new WebTask().execute("http://www.belstu.by/news.html?list=20&filter=1", ".TitleRef");
        adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.text_item, titleList);
    }

    public class WebTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg) {
            try
            {
                Document doc = Jsoup.connect(arg[0]).get();
                Elements titleContent = doc.select(arg[1]);

                for (Element contents: titleContent) {
                    titleList.add(contents.text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String results) {
            mListView.setAdapter(adapter);
        }
    }
}