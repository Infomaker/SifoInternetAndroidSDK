package mo.dyna.sifomobileanalyticssdkforandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import se.sifo.analytics.mobileapptagging.android.MobileTaggingFramework;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> mAdapter;
    String[] items;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileTaggingFramework.setLogPrintsActivated(true);
        MobileTaggingFramework.createInstance(getApplicationContext(), Contants.cpid, "TestApplicationSifoText1");

        setIds();
        makeList();
        setupAdapter();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), items[i] , Toast.LENGTH_LONG).show();
                if(MobileTaggingFramework.getInstance() != null){
                    MobileTaggingFramework.getInstance().sendTag(i + "");
                }
            }
        });

    }

    private void setIds() {
        mListView = (ListView) findViewById(R.id.list_view);
    }

    private void makeList() {
        items = new String[30];
        for (int i = 0; i < 30; i++) {
            items[i] = i + " item";
        }
    }

    private void setupAdapter() {
        mAdapter =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        mListView.setAdapter(mAdapter);
    }


}
