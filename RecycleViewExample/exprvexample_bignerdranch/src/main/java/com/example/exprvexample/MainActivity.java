package com.example.exprvexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ParentObject> mCrimes;
    private EditText mEditText;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCrimes = new ArrayList<>();
        int index = 0;
        while (index < 5) {
            Crime crime = new Crime("Crime #" + index);
            for (int i = 0; i < 3; i++) {
                crime.addChild(new CrimeChild(new Date(i), false));
            }
            mCrimes.add(crime);
            index++;
        }

        mEditText = (EditText) findViewById(R.id.main_edit_text);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(generateAdapter());

        Button button = (Button) findViewById(R.id.main_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mEditText.getText().toString();
                if (TextUtils.isEmpty(input)) {
                    return;
                }
                CrimeChild crimeChild = new CrimeChild(new Date(), false);
                boolean isContainCrime = false;
                for (ParentObject parentObject : mCrimes) {
                    Crime crime = (Crime) parentObject;
                    if (crime.getTitle().equals(input)) {
                        crime.addChild(crimeChild);
                        isContainCrime = true;
                    }
                }
                if (!isContainCrime) {
                    Crime crime = new Crime(input);
                    crime.addChild(crimeChild);
                    mCrimes.add(crime);
                }
                mEditText.setText("");
                mRecyclerView.setAdapter(generateAdapter());
            }
        });

    }

    private CrimeExpandableAdapter generateAdapter() {
        CrimeExpandableAdapter adapter = new CrimeExpandableAdapter(this, mCrimes);
//        adapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
//        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);
        return adapter;
    }
}
