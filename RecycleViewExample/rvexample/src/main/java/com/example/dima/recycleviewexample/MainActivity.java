package com.example.dima.recycleviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Person> mPersonList;

    private RVAdapter mRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPersonList = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mRVAdapter = new RVAdapter(mPersonList);
        recyclerView.setAdapter(mRVAdapter);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.edit_text_name);
                EditText phone = (EditText) findViewById(R.id.edit_text_phone);
                mPersonList.add(new Person(name.getText().toString(), phone.getText().toString()));

                mRVAdapter.notifyDataSetChanged();

                name.setText("");
                phone.setText("");
            }
        });

    }
}
