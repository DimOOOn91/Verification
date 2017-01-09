package com.example.test2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "myLog";
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.lvMain);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.names, android.R.layout.simple_list_item_1);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(LOG_TAG, "itemClick: position = " + position + ", id = " + id);
            }
        });

        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(LOG_TAG, "itemSelected: position = " + position + ", id = " + id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d(LOG_TAG, "itemSelected: nothing");
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                Log.d(LOG_TAG, "scrollState = " + scrollState);
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
//                Log.d(LOG_TAG, "scroll: firstVisibleItem = " + firstVisibleItem
//                + ", visibleItemCount = " + visibleItemCount
//                + ", totalItemCount = " + totalItemCount);
            }
        });
    }
}
