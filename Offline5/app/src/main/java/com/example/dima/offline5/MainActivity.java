package com.example.dima.offline5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private AdapterTest mAdapterTest;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private ArrayList<String> mList;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this, 5, GridLayout.VERTICAL, false);
        mStaggeredGridLayoutManager  = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        for (int i = 0; i < 31; i++) {
            if (i % 2 == 0) {
                mList.add("itemitemitemitemitemitemitem - " + i);
            } else if (i % 3 == 0) {
                mList.add("itemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitem - " + i);
            } else {
                mList.add("item - " + i);
            }
        }

        mAdapterTest = new AdapterTest(this, mList);
        mAdapterTest.setOnClickTestAdapter(new AdapterTest.OnClickTestAdapter() {
            @Override
            public void onClick(int position) {
//                Toast.makeText(getApplicationContext(), mList.get(position), Toast.LENGTH_SHORT).show();
                mList.remove(position);
                mAdapterTest.updateRemove(position);
            }
        });
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapterTest);

        mButton = (Button) findViewById(R.id.btn);
        mButton.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < 20; i++) {
            mList.add("add - " + i);
        }
        mAdapterTest.updateList(mList);
    }
}
