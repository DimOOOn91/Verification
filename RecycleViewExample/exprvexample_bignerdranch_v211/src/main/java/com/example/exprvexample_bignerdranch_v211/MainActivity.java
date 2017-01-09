package com.example.exprvexample_bignerdranch_v211;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Input> mInputs;
    private RecyclerView mRecyclerView;
    private InputExpandableAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputs = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new InputExpandableAdapter(this, mInputs);
        mRecyclerView.setAdapter(mAdapter);

        Button button = (Button) findViewById(R.id.main_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.main_edit_text);
                String text = editText.getText().toString();
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(MainActivity.this, "Please enter some text to add!", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isContainInput = false;
                for (Input input : mInputs) {
                    if (text.equals(input.getName())) {
                        input.addChildren(new Date());
                        isContainInput = true;
                    }
                }
                if (!isContainInput) {
                    Input input = new Input(text);
                    input.addChildren(new Date());
                    mInputs.add(input);
                }
                editText.setText("");
                mAdapter = new InputExpandableAdapter(MainActivity.this, mInputs);
                mRecyclerView.setAdapter(mAdapter);
//                mAdapter.notifyDataSetChanged();
            }
        });

    }
}
