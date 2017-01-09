package com.example.dima.listviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "myLogs";

    private ListView mListView;
    private String[] mNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.names,
                android.R.layout.simple_list_item_multiple_choice);
        mListView.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray sparseBooleanArray = mListView.getCheckedItemPositions();
                for (int i = 0; i < sparseBooleanArray.size(); i++) {
                    int key = sparseBooleanArray.keyAt(i);
                    if (sparseBooleanArray.get(key)) {
                        Log.d(LOG_TAG, mNames[key]);
                    }
                }
            }
        });

        mNames = getResources().getStringArray(R.array.names);

    }


}
